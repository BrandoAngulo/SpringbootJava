package integradorrecaudo.utilidades.exceptiones;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import integradorrecaudo.utilidades.dto.HTTPGenericoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<HTTPGenericoDTO> runtimeException(RuntimeException ex) {

        log.error(ex.getMessage());
        log.error(ex.getClass().toString());
        return HTTPGenericoDTO.error(
                EMensajesExcepciones.ERROR_DESCONOCIDO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<HTTPGenericoDTO> requestException(RequestException ex) {
        return HTTPGenericoDTO.error(
                ex.getEMensajesExcepciones(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<HTTPGenericoDTO> accessDeniedException() {

        return HTTPGenericoDTO.error(
                EMensajesExcepciones.PERMISOS_DENEGADOS, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<HTTPGenericoDTO> jWTVerificationException(JWTVerificationException ex) {

        if (ex instanceof TokenExpiredException) {
            return HTTPGenericoDTO.error(
                    EMensajesExcepciones.TOKEN_EXPIRO, HttpStatus.UNAUTHORIZED);
        } else {
            return HTTPGenericoDTO.error(
                    EMensajesExcepciones.TOKEN_INVALIDO, HttpStatus.UNAUTHORIZED);
        }


    }

    @ExceptionHandler(value = NoFoundException.class)
    public ResponseEntity<HTTPGenericoDTO> noFoundException(NoFoundException ex) {
        return HTTPGenericoDTO.error(
                ex.getEMensajesExcepciones(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RequestExceptionString.class)
    public ResponseEntity<HTTPGenericoDTO> requestExceptionString(RequestExceptionString ex) {
        return HTTPGenericoDTO.errorString(
                ex.getMensajeError(), HttpStatus.BAD_REQUEST);
    }
}
