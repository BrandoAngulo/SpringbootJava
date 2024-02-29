package com.microservicios.cliente.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cliente")
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigocliente")
    private Integer codigoCliente;
//    private Integer codigoCredito;
    private String nombre;
    private String apellido;
    private String celular;
    @Column(name = "tipodocumento", length = 2)
    private String tipoDocumento;
    private String documento;


}
