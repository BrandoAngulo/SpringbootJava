package integradorrecaudo.modulos.service.impl;

import integradorrecaudo.modulos.dto.ModulosResponseDTO;
import integradorrecaudo.modulos.entity.Modulos;
import integradorrecaudo.modulos.mapper.ModulosMapper;
import integradorrecaudo.modulos.repository.ModulosRepository;
import integradorrecaudo.modulos.service.ModulosService;
import integradorrecaudo.submodulos.dto.SubmodulosResponseDTO;
import integradorrecaudo.submodulos.entity.Submodulos;
import integradorrecaudo.submodulos.mapper.SubmodulosMapper;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ModulosServiceImpl implements ModulosService {

    private final ModulosRepository modulosRepository;

    public ModulosServiceImpl(ModulosRepository modulosRepository) {
        this.modulosRepository = modulosRepository;
    }

    @Override
    public Set<ModulosResponseDTO> consultarTodos() {
        log.info("consultar modulos: ");
        try {
            return modulosRepository.findAll().stream()
                    .map(modulos -> {
                        ModulosResponseDTO modulosResponseDTO = ModulosMapper.MODULOS.toModulosResponseDTO(modulos);
                        Set<SubmodulosResponseDTO> submodulos = modulos.getSubmodulos()
                                .stream()
                                .map(SubmodulosMapper.SUBMODULOS::toSubmodulosResponseDTO)
                                .sorted(Comparator.comparing(SubmodulosResponseDTO::getTitulo))
                                .collect(Collectors.toCollection(LinkedHashSet::new));
                        modulosResponseDTO.setSubmodulos(submodulos);
                        return modulosResponseDTO;
                    })
                    .sorted(Comparator.comparing(ModulosResponseDTO::getTitulo))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

        } catch (RuntimeException ex) {
            log.error("consultar modulos: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Set<Modulos> consultarModulosByRol(Set<String> roles) {
        log.info("consultarModulosByRol: {}", roles);

        Set<Tuple> rolesBd = modulosRepository.consultarModulosAsignados(roles);


        Map<Integer, Modulos> listaModulos = new HashMap<>();
        try {

            for (Tuple tuple : rolesBd) {
                Integer idModulo = (Integer.parseInt(tuple.get("id_modulo").toString()));

                Modulos modulos = listaModulos.get(idModulo);
                if (modulos == null) {
                    modulos = Modulos.builder()
                            .id(idModulo)
                            .titulo(tuple.get("titulo_modulo", String.class))
                            .ruta((String) tuple.get("ruta_modulo"))
                            .icono(tuple.get("icono_modulo", String.class))
                            .submodulos(new HashSet<>()).build();

                    listaModulos.put(idModulo, modulos);
                }

                Submodulos submodulos = Submodulos.builder()
                        .id((Integer.parseInt(tuple.get("id_submodulo").toString())))
                        .titulo(tuple.get("titulo_submodulo", String.class))
                        .ruta(tuple.get("ruta_submodulo", String.class))
                        .icono(tuple.get("icono_submodulo", String.class))
                        .build();

                modulos.getSubmodulos().add(submodulos);
            }

            return new HashSet<>(listaModulos.values());
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
}
