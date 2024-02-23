package com.microservicios.credito.servicio.servicioImpl;

import com.microservicios.credito.modelo.Credito;
import com.microservicios.credito.repositorio.persistence.CreditoRepositorio;
import com.microservicios.credito.servicio.CreditoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Slf4j
@Service
public class CreditoServicioImpl implements CreditoService {

    private final CreditoRepositorio creditoRepositorio;

    @Override
    public List<Credito> listaCredito() {
        List<Credito> listaCreditos = creditoRepositorio.findAll();

        return listaCreditos;
    }

    @Override
    public List<Credito> listaCreditosCliente(Integer codigoCliente) {

        return creditoRepositorio.findByCodigoCliente(codigoCliente);
    }

    @Override
    public Credito buscarCredito(Integer codigoCredito) {
        Credito credito = creditoRepositorio.findById(codigoCredito).orElseThrow(() -> new RuntimeException());
        try {
            log.info("buscarCredito= " + credito);
            return credito;
        } catch (RuntimeException e) {
            log.error("buscarCredito= " + credito);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Credito guardarCredito(Credito credito) {
       return creditoRepositorio.save(credito);
    }

    @Override
    public void eliminarCredito(Integer codigoCredito) {

        Credito credito = creditoRepositorio.findById(codigoCredito).orElseThrow();

        creditoRepositorio.delete(credito);

    }

    @Override
    public Credito ActualizarCredito(Integer codigoCredito, Credito credito) {

        Credito credito1 = creditoRepositorio.findById(codigoCredito).orElseThrow();

        credito1.setDiasCredito(credito.getDiasCredito());
        credito1.setFechaCredito(credito.getFechaCredito());
        credito1.setValor(credito.getValor());
        credito1.setEstado(credito.getEstado());

        return creditoRepositorio.save(credito1);
    }
}
