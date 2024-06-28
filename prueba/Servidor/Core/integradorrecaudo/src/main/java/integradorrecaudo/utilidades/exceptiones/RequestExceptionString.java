package integradorrecaudo.utilidades.exceptiones;

import lombok.Getter;

@Getter
public class RequestExceptionString extends RuntimeException {
    private final String mensajeError;

    public RequestExceptionString(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
