package integradorrecaudo.utilidades.dto;

import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import integradorrecaudo.utilidades.constantes.Constantes;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
public class HTTPGenericoDTO {

    private Integer status;
    private Object payload;

    public static ResponseEntity<HTTPGenericoDTO> correcto(Object data) {

        HTTPGenericoDTO httpGenericoDTO = new HTTPGenericoDTO();
        httpGenericoDTO.setStatus(HttpStatus.OK.value());
        httpGenericoDTO.setPayload(data);

        return new ResponseEntity<>(httpGenericoDTO, HttpStatus.OK);
    }

    public static ResponseEntity<HTTPGenericoDTO> error(
            EMensajesExcepciones eMensajesExcepciones, HttpStatus httpStatus) {

        Map<String, Object> generarError = new HashMap<>();
        generarError.put(Constantes.MENSAJE, eMensajesExcepciones.getMensaje());
        generarError.put(Constantes.RECOMENDACION, eMensajesExcepciones.getRecomendacion());
        generarError.put(Constantes.CODIGO, eMensajesExcepciones.getCodigo());

        HTTPGenericoDTO genericErrorDto = new HTTPGenericoDTO();
        genericErrorDto.setPayload(generarError);
        genericErrorDto.setStatus(httpStatus.value());

        return new ResponseEntity<>(genericErrorDto, httpStatus);
    }

    public static ResponseEntity<HTTPGenericoDTO> errorString(
            String mensajeError, HttpStatus httpStatus) {

        Map<String, Object> generarError = new HashMap<>();
        generarError.put(Constantes.MENSAJE, mensajeError);
        generarError.put(Constantes.RECOMENDACION, "Verifique la informacion ingresada");

        HTTPGenericoDTO genericErrorDto = new HTTPGenericoDTO();
        genericErrorDto.setPayload(generarError);
        genericErrorDto.setStatus(httpStatus.value());

        return new ResponseEntity<>(genericErrorDto, httpStatus);
    }

}

