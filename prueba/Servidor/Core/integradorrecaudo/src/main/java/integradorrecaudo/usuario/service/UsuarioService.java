/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradorrecaudo.usuario.service;


import integradorrecaudo.usuario.dto.CambiarContrasenaDTO;
import integradorrecaudo.usuario.dto.RegistrarUsuarioDTO;
import integradorrecaudo.usuario.dto.UsuarioDTO;

import java.util.List;

/**
 * @author desarrollo
 */
public interface UsuarioService {

    UsuarioDTO registrar(RegistrarUsuarioDTO registrarUsuarioDTO);
    List<UsuarioDTO> consultarTodos();
    UsuarioDTO consultarXId(Integer id);
    UsuarioDTO actualizar(UsuarioDTO usuarioDTO);
    boolean cambiarEstado(int id);
    String cambiarContrasena(CambiarContrasenaDTO cambiarContrasenaDTO);


}
