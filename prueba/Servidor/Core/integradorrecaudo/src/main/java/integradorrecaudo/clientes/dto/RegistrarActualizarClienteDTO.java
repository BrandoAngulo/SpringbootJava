package integradorrecaudo.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import integradorrecaudo.utilidades.constantes.Constantes;
import jakarta.validation.constraints.Email;



public record RegistrarActualizarClienteDTO(

        Integer id,
        @JsonProperty("nombre_admin")
        String nombreAdmin, String celular,
        @Email(regexp = Constantes.REGEX_EMAIL)
        String email,
        String documento,
        @JsonProperty("documento_facturacion")
        String documentoFacturacion,
        @JsonProperty("tipo_factura")
        String tipoFactura,
        @JsonProperty("tipo_moneda")
        String tipoMoneda,
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
        @JsonProperty("id_servidor")
        Integer servidor,
        @JsonProperty("id_zona_horaria")
        Integer zonaHoraria,
        @JsonProperty("id_version")
        Integer version) {
}
