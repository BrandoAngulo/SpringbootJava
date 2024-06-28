package integradorrecaudo.log.controller;

import integradorrecaudo.log.service.LogService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/logs")
@Tag(name = "Logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping(value = "consultar-todos")
    //@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(logService.consultarLogs());
    }

    @GetMapping(value = "descargar-archivo")
    //@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Resource> descargarArchivo(@RequestParam String nombreArchivo) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; nombreArchivo=".concat(nombreArchivo))
                .body(logService.descargarArchivo(nombreArchivo));

    }

}
