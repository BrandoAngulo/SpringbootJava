package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon2.hash(1, 1024, 1, usuario.getPass());
        usuario.setPass(hash);

        usuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if (!validarToken(token)) {
            System.out.println("Entro"+true);
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/buscar/{id}")
    public Usuario buscar(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return usuario;
    }

    @RequestMapping(value = "api/editar/{id}")
    public Usuario editar(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Prueba");
        usuario.setApellido("Prrr");
        usuario.setCelular("3122555466");
        usuario.setCorreo("purbe@prueba.com");
        return usuario;
    }

    @RequestMapping(value = "api/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        usuarioDao.eliminar(id);
    }
}
