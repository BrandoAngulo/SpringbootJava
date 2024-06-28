package integradorrecaudo.servidores.controller;


import integradorrecaudo.servidores.service.ServidorService;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidores")
@Tag(name = "Servidores")
@AllArgsConstructor
public class ServidoresController {

    private final ServidorService servidorService;

    @GetMapping(value = "/consultar-todos")
    public ResponseEntity<HTTPGenericoDTO> consultarTodos() {
        return HTTPGenericoDTO.correcto(servidorService.consultarTodos());
    }
}
