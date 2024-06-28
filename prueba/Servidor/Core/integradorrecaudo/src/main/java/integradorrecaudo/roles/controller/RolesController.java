package integradorrecaudo.roles.controller;

import integradorrecaudo.roles.dto.ActualizarRol;
import integradorrecaudo.roles.dto.AsignarPermisosDTO;
import integradorrecaudo.roles.dto.CrearRolDTO;
import integradorrecaudo.roles.service.RolesService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles")
@AllArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @PostMapping(value = "registrar")
//    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> registrar(@RequestBody CrearRolDTO crearRolDTO) {
        return HTTPGenericoDTO.correcto(rolesService.crearRol(crearRolDTO));
    }

    @GetMapping(value = "consultar-nombre-roles")
//    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> consultarNombreRoles() {
        return HTTPGenericoDTO.correcto(rolesService.consultarNombreRoles());
    }

    @PutMapping(value = "asignar-permisos")
//    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> asignarPermisos(@RequestBody AsignarPermisosDTO asignarPermisosDTO) {
        return HTTPGenericoDTO.correcto(rolesService.asignarPermisos(asignarPermisosDTO));
    }

    @GetMapping(value = "consultar-roles-modulos-asignados")
//    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> consultarRolesModulosAsignados() {
        return HTTPGenericoDTO.correcto(rolesService.consultarRolesModulosAsignados());
    }

    @PutMapping(value = "cambiar-estado/{id}")
    public ResponseEntity<HTTPGenericoDTO> cambiarEstado(@PathVariable Integer id) {
        return HTTPGenericoDTO.correcto(rolesService.cambiarEstado(id));
    }

    @PutMapping(value = "actualizar")
    public ResponseEntity<HTTPGenericoDTO> actualizar(@RequestBody ActualizarRol actualizarRol) {
        return HTTPGenericoDTO.correcto(rolesService.actualizar(actualizarRol));
    }


}
