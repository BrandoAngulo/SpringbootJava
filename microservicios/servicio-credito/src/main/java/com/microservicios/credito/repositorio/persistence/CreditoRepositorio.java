package com.microservicios.credito.repositorio.persistence;

import com.microservicios.credito.modelo.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CreditoRepositorio extends JpaRepository<Credito, Integer> {
    List<Credito> findByCodigoCliente(Integer codigoCliente);
}
