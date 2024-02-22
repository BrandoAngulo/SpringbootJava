package com.microservicios.credito.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credito")
public class Credito {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigocredito")
    private Integer codigoCredito;
    @Column(name = "codigocliente")
    private Integer codigoCliente;
    @Column(name = "fechacredito")
    private String fechaCredito;
    private BigDecimal valor;
    @Column(name = "diascredito")
    private Integer diasCredito;
    private String estado;
}
