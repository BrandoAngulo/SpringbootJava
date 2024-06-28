package integradorrecaudo.auth.controller;

import integradorrecaudo.usuario.dto.LoginRequestDTO;
import integradorrecaudo.usuario.service.impl.UsuarioServiceImpl;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion")
@AllArgsConstructor
public class Auth {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping(value = "login")
    public ResponseEntity<HTTPGenericoDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return HTTPGenericoDTO.correcto(usuarioService.login(loginRequestDTO));
    }

}