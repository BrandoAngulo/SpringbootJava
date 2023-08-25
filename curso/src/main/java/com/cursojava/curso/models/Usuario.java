package com.cursojava.curso.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String celular;
    @Column
    private String correo;
    @Column
    private String pass;

}
