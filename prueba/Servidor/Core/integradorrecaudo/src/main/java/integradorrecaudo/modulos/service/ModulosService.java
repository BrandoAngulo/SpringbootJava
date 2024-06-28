package integradorrecaudo.modulos.service;

import integradorrecaudo.modulos.dto.ModulosResponseDTO;
import integradorrecaudo.modulos.entity.Modulos;

import java.util.Set;

public interface ModulosService {

    Set<ModulosResponseDTO> consultarTodos();
    Set<Modulos> consultarModulosByRol(Set<String> roles);
}
