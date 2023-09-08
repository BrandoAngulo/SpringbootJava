package com.amigoscode.springbootexmaple.entities;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "clientes")
public class Customers implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "cliente_id_sequence", sequenceName = "cliente_id_sequence", allocationSize = 1)
    private Integer id;

    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String numero;
    @Column
    private String pass;

}
