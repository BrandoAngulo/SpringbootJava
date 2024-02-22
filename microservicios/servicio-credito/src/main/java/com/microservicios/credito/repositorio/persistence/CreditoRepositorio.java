package com.microservicios.credito.repositorio.persistence;

import com.microservicios.credito.modelo.Credito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditoRepositorio extends CrudRepository<Credito, Integer> {
    List<Credito> findByCodigoCliente(Integer codigoCliente);
}
