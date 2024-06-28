package integradorrecaudo.servidores.mapper;

import integradorrecaudo.servidores.dto.ServidorDTO;
import integradorrecaudo.servidores.entity.Servidor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ServidorMapper {

    ServidorMapper SERVIDOR = Mappers.getMapper(ServidorMapper.class);

    Servidor toServidor(ServidorDTO servidorDTO);

    ServidorDTO toServidorDTO(Servidor servidor);

}
