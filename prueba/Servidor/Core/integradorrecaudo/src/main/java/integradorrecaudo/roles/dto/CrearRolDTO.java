package integradorrecaudo.roles.dto;

import java.util.Set;


public record CrearRolDTO(String rol, Set<Integer> permisos, Set<Integer> submodulos
) {
}
