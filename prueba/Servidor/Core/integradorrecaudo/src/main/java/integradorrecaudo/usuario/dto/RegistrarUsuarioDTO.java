/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradorrecaudo.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import integradorrecaudo.utilidades.constantes.Constantes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record RegistrarUsuarioDTO(
        @NotBlank
        @JsonProperty("nombre_usuario")
        String nombreUsuario,
        @NotBlank
        String contrasena,
        @NotBlank
        String nombres,
        @NotBlank
        String apellidos,
        @Email(regexp = Constantes.REGEX_EMAIL)
        String correo,
        @NotBlank
        String documento,
        @NotBlank
        Set<String> roles) {
}
