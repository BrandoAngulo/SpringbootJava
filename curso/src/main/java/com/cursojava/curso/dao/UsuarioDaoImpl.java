package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {

        String query = "FROM Usuario";
        List<Usuario> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void eliminar(int id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario obtenerUsuarioCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE correo = :correo";
        List<Usuario> resultado = entityManager.createQuery(query)
                .setParameter("correo", usuario.getCorreo())
                .getResultList();
        if (resultado.isEmpty()) {
            return null;
        }
        String passwordashed = resultado.get(0).getPass();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        if (argon2.verify(passwordashed, usuario.getPass())){
            return resultado.get(0);
        }else {
            return null;
        }

    }

}
