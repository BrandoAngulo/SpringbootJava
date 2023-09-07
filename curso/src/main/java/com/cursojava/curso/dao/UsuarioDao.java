package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();
    void registrarUsuario(Usuario usuario);

    void eliminar(int id);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
