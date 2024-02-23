package com.microservicios.cliente.repositorio;

import com.microservicios.cliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Integer, Cliente> {

}
