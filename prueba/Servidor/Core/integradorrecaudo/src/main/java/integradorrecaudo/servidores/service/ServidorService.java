package integradorrecaudo.servidores.service;

import integradorrecaudo.servidores.dto.ServidorDTO;

import java.util.List;

public interface ServidorService {

    List<ServidorDTO> consultarTodos();

    String ejecutarShell();


}
