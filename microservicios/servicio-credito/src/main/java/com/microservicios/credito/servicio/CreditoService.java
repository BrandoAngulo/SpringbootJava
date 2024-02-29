package com.microservicios.credito.servicio;

import com.microservicios.credito.modelo.Credito;
import com.microservicios.credito.repositorio.persistence.CreditoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CreditoService {

    List<Credito> listaCredito();
    List<Credito> listaCreditosCliente(Integer codigoCliente);
    Credito buscarCredito(Integer codigoCredito);
    Credito guardarCredito(Credito credito);
    void eliminarCredito(Integer credito);
    Credito ActualizarCredito(Integer codigoCredito, Credito credito);

}
