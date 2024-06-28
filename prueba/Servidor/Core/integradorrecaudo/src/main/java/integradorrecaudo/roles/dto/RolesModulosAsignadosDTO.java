package integradorrecaudo.roles.dto;

import integradorrecaudo.modulos.dto.ModulosResponseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class RolesModulosAsignadosDTO {
    Integer id;
    String rol;
    boolean activo;
    Set<ModulosResponseDTO> modulos;
}
