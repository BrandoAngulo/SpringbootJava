package com.seguridadjwt.curso2.repositories;

import com.seguridadjwt.curso2.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    //para buscar en la clase de usuario por el correo
    Optional<Usuarios> findByCorreo(String correo);
}
