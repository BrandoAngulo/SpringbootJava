package integradorrecaudo.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import integradorrecaudo.modulos.entity.Modulos;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponseDTO {

    Integer id;
    @JsonProperty("nombre_usuario")
    String nombreUsuario;
    String nombres;
    String apellidos;
    String correo;
    String documento;
    Set<String> roles;
    String token;
    Set<Modulos> modulos;
}
