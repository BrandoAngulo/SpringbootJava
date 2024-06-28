package integradorrecaudo.roles.dto;

import integradorrecaudo.permisos.entity.Permisos;

import java.util.Set;

public record ActualizarRol(Integer id, String rol, Set<Permisos> permisos, Set<Integer> submodulos) {
}
