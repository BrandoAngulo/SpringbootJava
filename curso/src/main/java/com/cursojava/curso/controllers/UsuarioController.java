package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "usuaurio")
    public Usuario getUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        usuario.setPass("123456789");

        return  usuario;
    }
}
