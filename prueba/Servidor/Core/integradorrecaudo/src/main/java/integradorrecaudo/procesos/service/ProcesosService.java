package integradorrecaudo.procesos.service;

import integradorrecaudo.procesos.dto.ProcesosDTO;

import java.util.List;

public interface ProcesosService {

    List<ProcesosDTO> consultarTodosProcesos();
}
