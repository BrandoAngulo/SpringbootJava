package integradorrecaudo.roles.service;

import integradorrecaudo.roles.dto.AsignarPermisosDTO;
import integradorrecaudo.roles.dto.CrearRolDTO;
import integradorrecaudo.roles.dto.ActualizarRol;
import integradorrecaudo.roles.dto.RolesModulosAsignadosDTO;
import integradorrecaudo.roles.entity.Roles;

import java.util.List;
import java.util.Set;

public interface RolesService {

    Set<String> consultarNombreRoles();

    String crearRol(CrearRolDTO crearRolDTO);

    Roles asignarPermisos(AsignarPermisosDTO asignarPermisosDTO);

    List<String> consultarPermisoEndpoints(int id);

    Roles actualizar(ActualizarRol actualizarRol);

    Set<RolesModulosAsignadosDTO> consultarRolesModulosAsignados();

    boolean cambiarEstado(Integer id);
}
