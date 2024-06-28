package integradorrecaudo.procesos.mapper;

import integradorrecaudo.procesos.dto.ProcesosDTO;
import integradorrecaudo.procesos.entity.Procesos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProcesosMapper {

    ProcesosMapper PROCESOS = Mappers.getMapper(ProcesosMapper.class);

    Procesos toProcesos(ProcesosDTO procesosDTO);

    ProcesosDTO toProcesosDTO(Procesos procesos);
    List<ProcesosDTO> toProcesosListDTO(List<Procesos> procesosList);
}
