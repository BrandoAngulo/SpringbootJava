package integradorrecaudo.modulos.dto;

import integradorrecaudo.submodulos.dto.SubmodulosResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulosResponseDTO {

    Integer id;
    String titulo;
    Set<SubmodulosResponseDTO> submodulos;
}
