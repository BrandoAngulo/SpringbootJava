package com.cursojava.curso.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String pass;


}
