package integradorrecaudo.versiones.service;

import integradorrecaudo.versiones.dto.VersionesDTO;

import java.util.List;

public interface VersionesService {

    List<VersionesDTO> consultarTodos();

    VersionesDTO registrarVersiones(VersionesDTO versionesDTO);


    VersionesDTO actualizarVersiones(VersionesDTO versionesDTO) ;

    boolean cambiarEstado(Integer id);

}
