package integradorrecaudo.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;

public record ClienteDTO(
        Integer id,
        @JsonProperty("nombre_admin")
        String nombreAdmin,
        String celular, String email, String documento,
        @JsonProperty("documento_facturacion")
        String documentoFacturacion,
        @JsonProperty("tipo_factura")
        String tipoFactura,
        @JsonProperty("tipo_moneda")
        String tipoMoneda,
        @JsonProperty("fecha_creacion")
        Date fechaCreacion,
        @JsonProperty("tipo_cliente")
        Integer tipoCliente,
        @JsonProperty("nombre_java")
        String nombreJava,
        @JsonProperty("puerto_web")
        Integer puertoWeb,
        @JsonProperty("puerto_movil")
        Integer puertoMovil,
        @JsonProperty("nombre_web")
        String nombreWeb,
        boolean activo,
        String servidor,
        @JsonProperty("zona_horaria")
        String zonaHoraria,
        String version) {
}
