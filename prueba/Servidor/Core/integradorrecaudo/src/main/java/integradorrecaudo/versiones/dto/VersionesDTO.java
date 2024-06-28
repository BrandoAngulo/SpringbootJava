package integradorrecaudo.versiones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VersionesDTO(

        Integer id,
        @JsonProperty("nombre_version")
        String nombreVersion,
        @JsonProperty("java_version")
        String javaVersion,
        @JsonProperty("php_version")
        String phpVersion,
        @JsonProperty("movil_version")
        String movilVersion,
        String version,
        String fecha,
        boolean activo
) {


}
