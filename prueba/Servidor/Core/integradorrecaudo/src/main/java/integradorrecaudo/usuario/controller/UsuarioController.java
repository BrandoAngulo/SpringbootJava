package integradorrecaudo.usuario.controller;


import integradorrecaudo.usuario.dto.CambiarContrasenaDTO;
import integradorrecaudo.usuario.dto.RegistrarUsuarioDTO;
import integradorrecaudo.usuario.dto.UsuarioDTO;
import integradorrecaudo.usuario.service.UsuarioService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(value = "registrar")
    public ResponseEntity<HTTPGenericoDTO> registrar(@RequestBody RegistrarUsuarioDTO registrarUsuarioDTO) {
        return HTTPGenericoDTO.correcto(usuarioService.registrar(registrarUsuarioDTO));
    }

    @GetMapping(value = "consultar-usuarios")
    //@PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(6))")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(usuarioService.consultarTodos());
    }

    @GetMapping(value = "consultar-usuario/{id}")
   // @PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(9))")
    public ResponseEntity<HTTPGenericoDTO> consultarXId(@PathVariable Integer id) {
        return HTTPGenericoDTO.correcto(usuarioService.consultarXId(id));
    }

    @PutMapping(value = "actualizar")
   // @PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(7))")
    public ResponseEntity<HTTPGenericoDTO> actualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return HTTPGenericoDTO.correcto(usuarioService.actualizar(usuarioDTO));
    }

    @PutMapping(value = "cambiar-estado/{id}")
   // @PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(8))")
    public ResponseEntity<HTTPGenericoDTO> cambiarEstado(@PathVariable Integer id) {
        return HTTPGenericoDTO.correcto(usuarioService.cambiarEstado(id));
    }

    @PutMapping(value = "cambiar-contrasena")
    public ResponseEntity<HTTPGenericoDTO> cambiarContrasena(@RequestBody CambiarContrasenaDTO cambiarContrasenaDTO) {
        return HTTPGenericoDTO.correcto(usuarioService.cambiarContrasena(cambiarContrasenaDTO));
    }

}
