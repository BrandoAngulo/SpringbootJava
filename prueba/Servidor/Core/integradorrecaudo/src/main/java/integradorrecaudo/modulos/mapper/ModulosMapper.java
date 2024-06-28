package integradorrecaudo.modulos.mapper;

import integradorrecaudo.modulos.dto.ModulosResponseDTO;
import integradorrecaudo.modulos.entity.Modulos;
import integradorrecaudo.submodulos.mapper.SubmodulosMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = SubmodulosMapper.class)
public interface ModulosMapper {

    ModulosMapper MODULOS = Mappers.getMapper(ModulosMapper.class);

    ModulosResponseDTO toModulosResponseDTO(Modulos modulos);

}

