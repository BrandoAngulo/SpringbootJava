package integradorrecaudo.utilidades.exceptiones;

import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestException extends RuntimeException {

    private final EMensajesExcepciones eMensajesExcepciones;

}
