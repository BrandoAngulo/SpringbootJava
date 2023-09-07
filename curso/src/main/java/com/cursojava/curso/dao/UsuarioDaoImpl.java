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
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE correo = :correo";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("correo", usuario.getCorreo())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPass();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        if (argon2.verify(passwordHashed, usuario.getPass())) {
            return lista.get(0);
        }
        return null;
    }

}
