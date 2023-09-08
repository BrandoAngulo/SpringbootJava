package com.amigoscode.springbootexmaple.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomersResponseDTO {
    String nombre;
    String correo;
    String numero;

}
