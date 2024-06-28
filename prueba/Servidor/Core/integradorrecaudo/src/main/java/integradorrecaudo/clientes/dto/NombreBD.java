package integradorrecaudo.clientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NombreBD(
        @JsonProperty("nombre_base_datos")
        String nombreBaseDatos) {
}
