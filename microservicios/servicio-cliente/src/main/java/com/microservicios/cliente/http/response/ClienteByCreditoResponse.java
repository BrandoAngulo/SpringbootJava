package com.microservicios.cliente.http.response;

import com.microservicios.cliente.controlador.DTO.CreditoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteByCreditoResponse {

    private String nombreCliente;
    private String documento;
    private String celular;
    private List<CreditoDTO> listaCredito;
}
