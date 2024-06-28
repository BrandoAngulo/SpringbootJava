package integradorrecaudo.modulos.controller;

import integradorrecaudo.modulos.service.ModulosService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modulos")
@Tag(name = "Modulos")
@AllArgsConstructor
public class ModulosController {

    private final ModulosService modulosService;

    @GetMapping(value = "consultar-todos")
    //@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(modulosService.consultarTodos());
    }

}
