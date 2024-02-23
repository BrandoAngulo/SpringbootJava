package com.microservicios.cliente.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String nombre;
    private String apellido;
    private String celular;
    @Column(name = "tipodocumento")
    private String tipoDocumento;
    private String documento;


}
