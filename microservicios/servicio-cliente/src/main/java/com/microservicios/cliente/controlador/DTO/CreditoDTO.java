package com.microservicios.cliente.controlador.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDTO {
    private Integer codigoCliente;
    private String fechaCredito;
    private BigDecimal valor;
    private Integer diasCredito;
    private String estado;
}