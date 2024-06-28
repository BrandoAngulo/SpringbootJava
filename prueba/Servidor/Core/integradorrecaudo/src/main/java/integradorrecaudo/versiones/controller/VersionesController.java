package integradorrecaudo.versiones.controller;

import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import integradorrecaudo.versiones.dto.VersionesDTO;
import integradorrecaudo.versiones.service.VersionesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/versiones")
@Tag(name = "Versiones")
@AllArgsConstructor
public class VersionesController {
    private final VersionesService versionesService;

    @GetMapping(value = "/consultar-todos")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(versionesService.consultarTodos());
    }

    @PostMapping(value = "/registar-versiones")
    public ResponseEntity<HTTPGenericoDTO> registrarVersiones(@RequestBody VersionesDTO versionesDTO) {
        return HTTPGenericoDTO.correcto(versionesService.registrarVersiones(versionesDTO));
    }

    @PutMapping(value = "/actualizar-versiones")
    public ResponseEntity<HTTPGenericoDTO> actualizarVersiones(@RequestBody VersionesDTO versionesDTO) {
        return HTTPGenericoDTO.correcto(versionesService.actualizarVersiones(versionesDTO));
    }

    @PutMapping(value = "/cambiar-estado/{id}")
    public ResponseEntity<HTTPGenericoDTO> cambiarEstado(@PathVariable Integer id) {
        return HTTPGenericoDTO.correcto(versionesService.cambiarEstado(id));
    }
}
