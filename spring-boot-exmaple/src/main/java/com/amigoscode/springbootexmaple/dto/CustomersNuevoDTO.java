package com.amigoscode.springbootexmaple.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomersNuevoDTO {
    Integer id;
    String nombre;
    String correo;
    String numero;
    String pass;

}
