package com.microservicios.cliente.client;

import com.microservicios.cliente.controlador.DTO.CreditoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-credito", url = "localhost:8080/credito")
public interface CreditoCliente {

    @GetMapping("/lista-credito-cliente/{codigoCliente}")
    List<CreditoDTO> listaCreditosCliente(@PathVariable Integer codigoCliente);

}
