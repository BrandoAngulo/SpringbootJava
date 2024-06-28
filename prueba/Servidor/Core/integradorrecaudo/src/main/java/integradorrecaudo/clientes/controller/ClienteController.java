package integradorrecaudo.clientes.controller;

import integradorrecaudo.clientes.dto.RegistrarActualizarClienteDTO;
import integradorrecaudo.clientes.service.ClienteService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping(value = "/registrar")
    //@PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(2))")
    public ResponseEntity<HTTPGenericoDTO> registrar(@RequestBody RegistrarActualizarClienteDTO registrarActualizarClienteDTO) {

        return HTTPGenericoDTO.correcto(clienteService.registrar(registrarActualizarClienteDTO));
    }

    @GetMapping(value = "/consultar-todos")
   // @PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(1))")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(clienteService.consultarTodos());
    }

    @PutMapping(value = "actualizar")
    //@PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(3))")
    public ResponseEntity<HTTPGenericoDTO> actualizar(
            @RequestBody RegistrarActualizarClienteDTO registrarActualizarClienteDTO) {
        return HTTPGenericoDTO.correcto(clienteService.actualizar(registrarActualizarClienteDTO));
    }

    @PutMapping(value = "cambiar-estado/{id}")
    public ResponseEntity<HTTPGenericoDTO> cambiarEstado(@PathVariable Integer id) {
        return HTTPGenericoDTO.correcto(clienteService.cambiarEstado(id));
    }

    @GetMapping(value = "consultar-bases-datos")
   // @PreAuthorize("hasAnyRole(@rolesServiceImpl.consultarPermisoEndpoints(4))")
    public ResponseEntity<HTTPGenericoDTO> consultarBasesDatos() {
        return HTTPGenericoDTO.correcto(clienteService.consultarBasesDatos());
    }
}
