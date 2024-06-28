package integradorrecaudo.log.service;

import org.springframework.core.io.Resource;

import java.util.List;

public interface LogService {

    List<String> consultarLogs();

    Resource descargarArchivo(String nombreArchivo);


}
