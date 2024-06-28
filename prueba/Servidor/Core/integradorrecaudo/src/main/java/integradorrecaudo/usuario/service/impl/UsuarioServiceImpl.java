/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradorrecaudo.usuario.service.impl;


import integradorrecaudo.modulos.entity.Modulos;
import integradorrecaudo.modulos.service.ModulosService;
import integradorrecaudo.roles.entity.Roles;
import integradorrecaudo.roles.repository.RolesRepository;
import integradorrecaudo.roles.service.impl.RolesServiceImpl;
import integradorrecaudo.seguridad.jwt.JwtUtils;
import integradorrecaudo.submodulos.entity.Submodulos;
import integradorrecaudo.usuario.dto.*;
import integradorrecaudo.usuario.entity.Usuario;

import java.util.*;


import integradorrecaudo.usuario.mapper.UsuarioMapper;
import integradorrecaudo.utilidades.exceptiones.NoFoundException;
import integradorrecaudo.utilidades.exceptiones.RequestException;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import integradorrecaudo.usuario.service.UsuarioService;
import integradorrecaudo.usuario.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final JwtUtils jwtUtils;
    private final ModulosService modulosService;

    @PostConstruct
    public void init() {
        registrarUsuarioInicial();
    }

    private void registrarUsuarioInicial() {
        log.info("registrarUsuarioInicial: ");
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(1));

        Usuario usuario = Usuario.builder()
                .nombreUsuario("maelo")
                .documento("1010")
                .nombres("maelito")
                .apellidos("blandon")
                .correo("maelo@gmail.com")
                .contrasena(passwordEncoder.encode("12345678"))
                .roles(roles)
                .activo(true)
                .build();

        usuarioRepository.save(usuario);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new RequestException(EMensajesExcepciones.AUTENTICACION_FALLIDA));
        return new User(
                usuario.getNombreUsuario(),
                usuario.getContrasena(),
                usuario.isActivo(),
                true,
                true,
                true,
                getAuthorities(usuario.getRoles()));
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        log.info("login: ".concat(loginRequestDTO.toString()));
        Usuario usuario = usuarioRepository.findByCorreo(loginRequestDTO.correo())
                .orElseThrow(() -> new RequestException(EMensajesExcepciones.AUTENTICACION_FALLIDA));


        if (!usuario.isActivo()) {
            throw new RequestException(EMensajesExcepciones.USUARIO_INACTIVO);
        }

        if (!passwordEncoder.matches(loginRequestDTO.password(), usuario.getContrasena())) {
            throw new RequestException(EMensajesExcepciones.AUTENTICACION_FALLIDA);
        }

        try {

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usuario.getNombreUsuario(), null, getAuthorities(usuario.getRoles()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Set<String> roles = usuario.getRoles().stream().map(
                    Roles::getRol).collect(Collectors.toSet());

            Set<Modulos> modulos = modulosService.consultarModulosByRol(roles);

            String token = jwtUtils.createToken(authentication);

            LoginResponseDTO loginResponseDTO = UsuarioMapper.USUARIO.toLoginResponseDTO(usuario);
            loginResponseDTO.setToken(token);
            loginResponseDTO.setRoles(roles);
            loginResponseDTO.setModulos(modulos);

            return loginResponseDTO;
        } catch (RuntimeException ex) {
            log.error("login ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Transactional()
    @Override
    public UsuarioDTO registrar(RegistrarUsuarioDTO registrarUsuarioDTO) {
        log.info("registrarUsuario: ".concat(registrarUsuarioDTO.toString()));
        Optional<Usuario> validarEmail = usuarioRepository.findByCorreo(registrarUsuarioDTO.correo());
        Optional<Usuario> validarUsuario = usuarioRepository.findByNombreUsuario(registrarUsuarioDTO.nombreUsuario());

        if (validarEmail.isPresent()) {
            throw new RequestException(EMensajesExcepciones.CORREO_REGISTRADO);
        }
        if (validarUsuario.isPresent()) {
            throw new RequestException(EMensajesExcepciones.USUARIO_REGISTRADO);
        }

        Set<Roles> roles = registrarUsuarioDTO.roles().stream()
                .map(role -> rolesRepository.findByRol(role).orElseThrow(
                        RuntimeException::new))
                .collect(Collectors.toSet());

        try {
            Usuario usuario = UsuarioMapper.USUARIO.toUsuario(registrarUsuarioDTO);
            usuario.setContrasena(passwordEncoder.encode(registrarUsuarioDTO.contrasena()));
            usuario.setRoles(roles);
            usuario.setActivo(true);

            UsuarioDTO usuarioDTO = UsuarioMapper.USUARIO.toUsuarioDTO(usuarioRepository.save(usuario));
            usuarioDTO.setRoles(registrarUsuarioDTO.roles());
            return usuarioDTO;

        } catch (RuntimeException ex) {
            log.error("registrarUsuario ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }


    @Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> consultarTodos() {
        log.info("consultarUsuarios: ");

        try {

            List<Usuario> usuarios = usuarioRepository.findAll();

            return usuarios.stream().map(
                    user -> UsuarioDTO.builder()
                            .id(user.getId())
                            .nombreUsuario(user.getNombreUsuario())
                            .apellidos(user.getApellidos())
                            .nombres(user.getNombres())
                            .correo(user.getCorreo())
                            .documento(user.getDocumento())
                            .roles(getAuthoritiesString(user.getRoles()))
                            .activo(user.isActivo())
                            .build()
            ).toList();

        } catch (RuntimeException ex) {
            log.error("consultarUsuarios: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO consultarXId(Integer id) {
        log.info("consultarUsuario: id ".concat(id.toString()));

        Usuario usuarioBD = usuarioRepository.findById(id).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.USUARIO_NO_ENCONTRADO));

        try {

            UsuarioDTO usuarioDTO = UsuarioMapper.USUARIO.toUsuarioDTO(usuarioBD);
            usuarioDTO.setRoles(getAuthoritiesString(usuarioBD.getRoles()));
            return usuarioDTO;

        } catch (RuntimeException ex) {

            log.error("consultarUsuario: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }


    }


    @Transactional
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {
        log.info("actualizarUsuario: ".concat(usuarioDTO.toString()));
        Usuario usuarioBD = usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.USUARIO_NO_ENCONTRADO));



        try {
            Usuario usuarioModificado = UsuarioMapper.USUARIO.usuarioDTOtoUsuario(usuarioDTO);
            usuarioModificado.setRoles(getAuthoritiesRoles(usuarioDTO.getRoles()));
            usuarioModificado.setActivo(true);
            usuarioModificado.setContrasena(usuarioBD.getContrasena());

            UsuarioDTO usuarioModificadoDTO = UsuarioMapper.USUARIO.toUsuarioDTO(usuarioRepository.save(usuarioModificado));
            usuarioModificadoDTO.setRoles(usuarioDTO.getRoles());
            return usuarioModificadoDTO;

        } catch (RuntimeException ex) {
            log.error("actualizarUsuario: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }


    }

    @Transactional
    @Override
    public boolean cambiarEstado(int id) {
        log.info("activarInactivarUsuario: ");
        Usuario usuarioBD = usuarioRepository.findById(id).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.USUARIO_NO_ENCONTRADO));
        try {
            usuarioBD.setActivo(!usuarioBD.isActivo());
            return usuarioRepository.save(usuarioBD).isActivo();
        } catch (RuntimeException ex) {
            log.error("activarInactivarUsuario: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional
    @Override
    public String cambiarContrasena(CambiarContrasenaDTO cambiarContrasenaDTO) {
        log.info("cambiarContrasena: ".concat(cambiarContrasenaDTO.toString()));
        Usuario usuarioBD = usuarioRepository.findById(cambiarContrasenaDTO.idUsuario()).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.USUARIO_NO_ENCONTRADO));

        if (!passwordEncoder.matches(cambiarContrasenaDTO.contrasenaAntigua(), usuarioBD.getContrasena())) {
            throw new RequestException(EMensajesExcepciones.CONTRASENA_ANTIGUA_INCORRECTA);
        }
        try {
            usuarioBD.setContrasena(passwordEncoder.encode(cambiarContrasenaDTO.contrasenaNueva()));
            usuarioRepository.save(usuarioBD);
            return "Contrasena modificada correctamente";
        } catch (RuntimeException ex) {
            log.error("cambiarContrasena: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(Set<Roles> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRol()))
                .collect(Collectors.toSet());
    }

    private Set<String> getAuthoritiesString(Set<Roles> roles) {
        return roles.stream().map(Roles::getRol).collect(Collectors.toSet());
    }

    private Set<Roles> getAuthoritiesRoles(Set<String> roles) {
        return roles.stream().map(role -> rolesRepository.findByRol(role).orElseThrow(
                
        )).collect(Collectors.toSet());
    }

}
