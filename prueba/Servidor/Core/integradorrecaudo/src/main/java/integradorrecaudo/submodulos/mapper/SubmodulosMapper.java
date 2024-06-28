package integradorrecaudo.submodulos.mapper;

import integradorrecaudo.submodulos.dto.SubmodulosResponseDTO;
import integradorrecaudo.submodulos.entity.Submodulos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SubmodulosMapper {
    SubmodulosMapper SUBMODULOS = Mappers.getMapper(SubmodulosMapper.class);

    SubmodulosResponseDTO toSubmodulosResponseDTO(Submodulos submodulos);
}
