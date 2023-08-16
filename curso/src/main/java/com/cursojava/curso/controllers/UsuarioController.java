package com.cursojava.curso.controllers;

import com.cursojava.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }@RequestMapping(value = "usuarios/")
    public List<Usuario> getUsuarios(){
        List <Usuario>usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(12L);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");

    Usuario usuario1 = new Usuario();
        usuario1.setId(15L);
        usuario1.setNombre("Prueba1");
        usuario1.setApellido("Prrr");
        usuario1.setCelular("3122555466");
        usuario1.setCorreo("purbe@prueba.com");

    Usuario usuario2 = new Usuario();
        usuario2.setId(16L);
        usuario2.setNombre("Prueba2");
        usuario2.setApellido("Prrr");
        usuario2.setCelular("3122555466");
        usuario2.setCorreo("purbe@prueba.com");

        usuarios.add(usuario);
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        return usuarios;
    }@RequestMapping(value = "buscar/{id}")
    public Usuario buscar(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }@RequestMapping(value = "editar/{id}")
    public Usuario editar(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }@RequestMapping(value = "eliminar/{id}")
    public Usuario eliminar(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }
}
