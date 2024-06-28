package integradorrecaudo.zona_horaria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ZonaHorariaMapper {

    ZonaHorariaMapper ZONA_HORARIA = Mappers.getMapper(ZonaHorariaMapper.class);
}
