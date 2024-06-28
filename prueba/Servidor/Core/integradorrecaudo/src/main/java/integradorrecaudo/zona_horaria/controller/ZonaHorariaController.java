package integradorrecaudo.zona_horaria.controller;

import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import integradorrecaudo.zona_horaria.service.ZonaHorariaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zona-horaria")
@Tag(name = "Zona horaria")
@AllArgsConstructor
public class ZonaHorariaController {
    private final ZonaHorariaService zonaHorariaService;

    @GetMapping(value = "/consultar-todos")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(zonaHorariaService.consultarTodos());
    }

}
