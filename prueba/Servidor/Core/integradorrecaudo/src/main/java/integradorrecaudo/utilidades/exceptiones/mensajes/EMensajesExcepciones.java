package integradorrecaudo.utilidades.exceptiones.mensajes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EMensajesExcepciones {

    USUARIO_REGISTRADO(
            "Usuario registrado",
            "E1",
            "verifique el nombre de usuario que se encuentra creando"),
    AUTENTICACION_FALLIDA(
            "Usuario o contraseña incorrectos",
            "E2",
            "verifique la informacion ingresada"),
    CORREO_REGISTRADO(
            "Correo registrado",
            "E3",
            "verifique el correo que se encuentra creando"),
    ERROR_DESCONOCIDO(
            "Error desconocido",
            "E4",
            "contacte al administrador del sistema"),
    USUARIO_INACTIVO(
            "Usuario inactivo",
            "E5",
            "contacte al administrador del sistema"),
    DATOS_NO_ENCONTRADOS(
            "Datos no encontrados",
            "E6",
            "revise los parametros de busqueda "),
    CORREO_DUPLICADO(
            "El correo ya se encuentra registrado.",
            "E7",
            "revise la informacion ingresada "),
    PERMISOS_DENEGADOS(
            "No tiene permisos para realizar esta accion.",
            "E8",
            "contacte al administrador del sistema"),
    ROL_YA_CREADO(
            "Rol ya se encuentra creado.",
            "E9",
            "revise la informacion ingresada"),
    CONTRASENA_ANTIGUA_INCORRECTA(
            "la contrasena antigua no es igual a la actual.",
            "E10",
            "revise la informacion ingresada"),
    USUARIO_NO_ENCONTRADO(
            "El usuario no se encuentra creado",
            "E11",
            "revise la informacion ingresada"),
    DOCUMENTO_CLIENTE_DUPLICADO(
            "El documento del cliente ya se encuentra registrado",
            "E12",
            "revise la informacion ingresada"),
    CORREO_CLIENTE_DUPLICADO(
            "El correo del cliente ya se encuentra registrado",
            "E13",
            "revise la informacion ingresada"),
    TOKEN_EXPIRO(
            "Token caduco",
            "E14",
            "Inicie sesion nuevamente"),
    TOKEN_INVALIDO(
            "Token invalido",
            "E15",
            "Verifique el token que esta enviando"),
    CLIENTE_NO_ENCONTRADO(
            "El cliente no se encuentra creado",
            "E16",
            "revise la informacion ingresada"),
    ROL_NO_EXISTE(
            "El rol no existe",
            "E17",
            "revise la informacion ingresada"),
    PERMISO_NO_EXISTE(
            "El permiso indicado no existe",
            "E18",
            "revise la informacion ingresada"),
    SUBMODULO_NO_EXISTE(
            "El submodulo indicado no existe",
            "E18",
            "revise la informacion ingresada"),
    MODULO_NO_EXISTE(
            "El modulo indicado no existe",
            "E18",
            "revise la informacion ingresada"),
    RUTA_INVALIDA_LOGS(
            "La ruta para consultar logs es invalida",
            "E18",
            "contacte el administrador"),
    VERSION_NO_EXISTE(
            "La version indicada no existe",
            "E19",
            "revise la informacion ingresada");

    private final String mensaje;
    private final String codigo;
    private final String recomendacion;
}
