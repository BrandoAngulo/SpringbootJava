package integradorrecaudo.roles.service.impl;

import integradorrecaudo.modulos.dto.ModulosResponseDTO;
import integradorrecaudo.permisos.entity.Permisos;
import integradorrecaudo.permisos.repository.PermisoRepository;
import integradorrecaudo.roles.dto.*;
import integradorrecaudo.roles.entity.Roles;
import integradorrecaudo.roles.repository.RolesRepository;
import integradorrecaudo.roles.service.RolesService;
import integradorrecaudo.submodulos.dto.SubmodulosResponseDTO;
import integradorrecaudo.submodulos.entity.Submodulos;
import integradorrecaudo.submodulos.repository.SubmodulosRepository;
import integradorrecaudo.utilidades.exceptiones.NoFoundException;
import integradorrecaudo.utilidades.exceptiones.RequestException;
import integradorrecaudo.utilidades.exceptiones.RequestExceptionString;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final PermisoRepository permisoRepository;
    private final SubmodulosRepository submodulosRepository;


    @Transactional(readOnly = true)
    @Override
    public Set<String> consultarNombreRoles() {
        log.info("consultar nombre de roles: ");
        try {
            Set<Roles> roles = new HashSet<>(rolesRepository.findAll());
            return roles.stream().map(Roles::getRol).collect(Collectors.toSet());
        } catch (RuntimeException e) {
            log.error("consultar nombre de roles: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Transactional
    @Override
    public String crearRol(CrearRolDTO crearRolDTO) {
        log.error("crear rol: {}", crearRolDTO.toString());

        String rol = crearRolDTO.rol().toUpperCase();
        if (rolesRepository.findByRol(rol).isPresent()) {
            throw new RequestException(EMensajesExcepciones.ROL_YA_CREADO);
        }

        try {
            Set<Submodulos> submodulos = crearRolDTO.submodulos().stream()
                    .map(permiso -> submodulosRepository.findById(permiso).orElseThrow(
                            () -> new NoFoundException(EMensajesExcepciones.PERMISO_NO_EXISTE)
                    )).collect(Collectors.toSet());

            Roles nuevoRol = new Roles();
            nuevoRol.setRol(rol.toUpperCase());
            nuevoRol.setPermisos(null);
            nuevoRol.setActivo(true);
            nuevoRol.setSubmodulos(submodulos);
            rolesRepository.save(nuevoRol);
            return "rol ".concat(rol.toUpperCase()).concat(" creado");

        } catch (RuntimeException ex) {
            log.error("crear rol: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public Roles asignarPermisos(AsignarPermisosDTO asignarPermisosDTO) {
        log.info("asignar permisos a roles: {}", asignarPermisosDTO);

        Roles roles = rolesRepository.findById(asignarPermisosDTO.idRol()).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.ROL_NO_EXISTE));
        try {
            Set<Permisos> permisos = asignarPermisosDTO.permisos().stream()
                    .map(permiso -> permisoRepository.findById(permiso).orElseThrow(
                            () -> new NoFoundException(EMensajesExcepciones.PERMISO_NO_EXISTE)
                    )).collect(Collectors.toSet());
            roles.setPermisos(permisos);
            return rolesRepository.save(roles);
        } catch (RuntimeException ex) {
            log.error("asignar permisos a roles: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }


    }

    /**
     * devuelve los roles que tienen permiso para ejecutar este endpoint
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> consultarPermisoEndpoints(int id) {
        try {
            List<String> roles = rolesRepository.consultarPermisos(id).stream().map(Roles::getRol).toList();

            log.info("consultar permisos: {}", roles);
            return roles;

        } catch (RuntimeException ex) {
            log.error("consultar permisos: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional()
    @Override
    public Roles actualizar(ActualizarRol actualizarRol) {
        Roles roles = rolesRepository.findById(actualizarRol.id()).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.ROL_NO_EXISTE));

        Set<Submodulos> submodulos = actualizarRol.submodulos().stream()
                .map(permiso -> submodulosRepository.findById(permiso).orElseThrow(
                        () -> new NoFoundException(EMensajesExcepciones.PERMISO_NO_EXISTE)
                )).collect(Collectors.toSet());

        try {
            roles.setSubmodulos(submodulos);
            roles.setRol(actualizarRol.rol().toUpperCase());

            return rolesRepository.save(roles);

        } catch (RuntimeException ex) {
            log.error("actualizar rol: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Set<RolesModulosAsignadosDTO> consultarRolesModulosAsignados() {

        try {

            List<Tuple> roles = rolesRepository.consultarSubmodulosAsignados();
            // Primero agrupamos los resultados por id_rol
            Map<Integer, List<Tuple>> agrupadosPorRol = roles.stream()
                    .collect(Collectors.groupingBy(tuple -> tuple.get("id_rol", Integer.class)));

            // Luego convertimos cada grupo a un RolesModulosAsignadosDTO
            Set<RolesModulosAsignadosDTO> modulosAsinados = agrupadosPorRol.entrySet().stream().map(entry -> {
                        Integer idRol = entry.getKey();
                        List<Tuple> tuples = entry.getValue();

                        RolesModulosAsignadosDTO rolDTO = new RolesModulosAsignadosDTO();
                        rolDTO.setId(idRol);
                        rolDTO.setRol(tuples.get(0).get("rol", String.class));
                        rolDTO.setActivo(tuples.get(0).get("activo", Boolean.class));

                        // Agrupamos los módulos dentro de cada rol
                        Map<Integer, List<Tuple>> agrupadosPorModulo = tuples.stream()
                                .filter(tuple -> tuple.get("id_modulo") != null) // Filtrar nulos para módulos
                                .collect(Collectors.groupingBy(tuple -> tuple.get("id_modulo", Integer.class)));

                        Set<ModulosResponseDTO> modulos = agrupadosPorModulo.entrySet().stream().map(moduloEntry -> {
                            Integer idModulo = moduloEntry.getKey();
                            List<Tuple> modTuples = moduloEntry.getValue();

                            ModulosResponseDTO moduloDTO = new ModulosResponseDTO();
                            moduloDTO.setId(idModulo);
                            moduloDTO.setTitulo(modTuples.get(0).get("titulo_modulo", String.class));

                            // Convertimos los submódulos dentro de cada módulo
                            Set<SubmodulosResponseDTO> submodulos = modTuples.stream()
                                    .filter(subTuple -> subTuple.get("id_submodulo") != null) // Filtrar nulos para submódulos
                                    .map(subTuple -> {
                                        SubmodulosResponseDTO submoduloDTO = new SubmodulosResponseDTO();
                                        submoduloDTO.setId(subTuple.get("id_submodulo", Integer.class));
                                        submoduloDTO.setTitulo(subTuple.get("titulo_submodulo", String.class));
                                        return submoduloDTO;
                                    })
                                    .collect(Collectors.toSet());

                            moduloDTO.setSubmodulos(submodulos);
                            return moduloDTO;
                        }).collect(Collectors.toSet());

                        rolDTO.setModulos(modulos);
                        return rolDTO;
                    }).sorted(Comparator.comparing(RolesModulosAsignadosDTO::getId))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            log.info("consultar modulos asignados a roles {}", modulosAsinados);
            return modulosAsinados;
        } catch (RuntimeException ex) {
            log.error("consultar modulos asignados a roles: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional
    @Override
    public boolean cambiarEstado(Integer id) {
        log.info("cambiar activo de rol: ");
        Roles rolBD = rolesRepository.findById(id).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.ROL_NO_EXISTE));

        if (!rolesRepository.consultarRolesAsignadosUsuariosPorIdRol(id).isEmpty()) {
            throw new RequestExceptionString(
                    String.format("El rol %s no se puede eliminar. Verifique que el rol no este asignado a un usuario", rolBD.getRol()));
        }
        try {
            rolBD.setActivo(!rolBD.isActivo());
            return rolesRepository.save(rolBD).isActivo();
        } catch (RuntimeException ex) {
            log.error("cambiar activo de rol: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }

}



