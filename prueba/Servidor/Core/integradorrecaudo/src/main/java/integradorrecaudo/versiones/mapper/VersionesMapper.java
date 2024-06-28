package integradorrecaudo.versiones.mapper;

import integradorrecaudo.versiones.dto.VersionesDTO;
import integradorrecaudo.versiones.entity.Versiones;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VersionesMapper {

    VersionesMapper VERSIONES = Mappers.getMapper(VersionesMapper.class);




    Versiones toRegistrarVersiones(VersionesDTO versionesDTO);

    VersionesDTO toVersionesDTO(Versiones versiones);

}
