package integradorrecaudo.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioDTO {

    Integer id;
    @JsonProperty("nombre_usuario")
    String nombreUsuario;

    String nombres;

    String apellidos;
    String correo;
    String contrasena;

    String documento;
    boolean activo;
    Set<String> roles;
}
