package integradorrecaudo.procesos.controller;

import integradorrecaudo.procesos.service.ProcesosService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/procesos")
@Tag(name = "procesos")
@AllArgsConstructor
public class ProcesosController {

    private final ProcesosService procesosService;

    @GetMapping(value = "/consultar-todos-procesos")
    public ResponseEntity<HTTPGenericoDTO> consultarTodosProcesos() {
        return HTTPGenericoDTO.correcto(procesosService.consultarTodosProcesos());
    }
}


