package integradorrecaudo.roles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record AsignarPermisosDTO(
        @JsonProperty("id_rol")
        Integer idRol,
        Set<Integer> permisos) {
}
