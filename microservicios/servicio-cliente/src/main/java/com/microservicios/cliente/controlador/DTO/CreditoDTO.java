package com.microservicios.cliente.controlador.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String celular;
    private String tipoDocumento;
    private String documento;
}
