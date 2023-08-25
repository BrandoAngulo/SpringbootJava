package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable int id){
        return  usuarioDao.getUsuario(id);
    }@RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }@RequestMapping(value = "api/buscar/{id}")
    public Usuario buscar(@PathVariable int id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }@RequestMapping(value = "api/editar/{id}")
    public Usuario editar(@PathVariable int id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return  usuario;
    }@RequestMapping(value = "api/eliminar/{id}")
    public void eliminar(@PathVariable int id){
        usuarioDao.eliminar(id);
    }
}
