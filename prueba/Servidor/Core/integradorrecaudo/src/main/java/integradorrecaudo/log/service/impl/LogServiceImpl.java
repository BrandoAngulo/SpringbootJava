package integradorrecaudo.log.service.impl;

import integradorrecaudo.log.service.LogService;
import integradorrecaudo.utilidades.exceptiones.RequestException;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Value("${archivos.ubicacion}")
    private String ubicacionArchivos;

    public List<String> consultarLogs() {
        try {
            Path directory = Paths.get(ubicacionArchivos);
            if (Files.exists(directory) && Files.isDirectory(directory)) {
                List<String> archivos = Files.list(directory)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".log"))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .toList();
                log.info("consultar logs: {}", archivos);
                return archivos;
            } else {
                throw new RequestException(EMensajesExcepciones.RUTA_INVALIDA_LOGS);
            }
        } catch (IOException ex) {
            log.error("consultar logs: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public Resource descargarArchivo(String nombreArchivo) {
        try {
            Path file = Paths.get(ubicacionArchivos).resolve(nombreArchivo);
            if (Files.exists(file)) {
                Resource archivo = new UrlResource(file.toUri());

                log.info("descargar archivo: {}", archivo.getFilename());
                return archivo;

            } else {
                throw new RequestException(EMensajesExcepciones.RUTA_INVALIDA_LOGS);
            }

        } catch (MalformedURLException | RuntimeException ex) {
            log.info("descargar archivo: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
