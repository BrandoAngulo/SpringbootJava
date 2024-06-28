package integradorrecaudo.tipo_documento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoDocumentoMapper {

    TipoDocumentoMapper TIPO_DOCUMENTO = Mappers.getMapper(TipoDocumentoMapper.class);
}
