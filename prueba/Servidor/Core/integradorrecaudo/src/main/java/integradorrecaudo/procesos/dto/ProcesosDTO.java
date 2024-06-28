package integradorrecaudo.procesos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProcesosDTO(
        Integer id,

        @JsonProperty("hora_fecha_actual")
        String hora_fecha_actual,

        String cliente,
        String procesos
        ) {
}
