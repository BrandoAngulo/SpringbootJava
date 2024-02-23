package com.microservicios.credito.controlador;

import com.microservicios.credito.modelo.Credito;
import com.microservicios.credito.repositorio.persistence.CreditoRepositorio;
import com.microservicios.credito.servicio.servicioImpl.CreditoServicioImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/credito")
public class CreditoControlador {

    private final CreditoServicioImpl creditoServicio;

    @GetMapping("/lista-credito")
    public List<Credito> listaCredito() {

        return creditoServicio.listaCredito();
    }

    @GetMapping("/lista-credito-cliente/{codigoCliente}")
    public List<Credito> listaCreditosCliente(@PathVariable Integer codigoCliente){
        return creditoServicio.listaCreditosCliente(codigoCliente);
    }

    @GetMapping("/buscar-credito/{codigoCredito}")
    public Credito buscarCredito(@PathVariable Integer codigoCredito){
        return creditoServicio.buscarCredito(codigoCredito);
    }

    @PostMapping("/guardar")
    public Credito guardarCredito(@RequestBody Credito credito) {
        return creditoServicio.guardarCredito(credito);
    }

    @DeleteMapping("/eliminar/{codigoCredito}")
    public void eliminarCredito(@PathVariable Integer codigoCredito) {
        creditoServicio.eliminarCredito(codigoCredito);
    }

    @PutMapping("/actualizar/{codigoCredito}")
    public Credito ActualizarCredito(@PathVariable Integer codigoCredito, @RequestBody Credito credito) {
        return creditoServicio.ActualizarCredito(codigoCredito, credito);
    }
}
