package integradorrecaudo.usuario.mapper;

import integradorrecaudo.roles.mapper.RolesMapper;
import integradorrecaudo.tipo_documento.mapper.TipoDocumentoMapper;
import integradorrecaudo.usuario.dto.LoginResponseDTO;
import integradorrecaudo.usuario.dto.RegistrarUsuarioDTO;
import integradorrecaudo.usuario.dto.UsuarioDTO;
import integradorrecaudo.usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RolesMapper.class, TipoDocumentoMapper.class})
public interface UsuarioMapper {

    UsuarioMapper USUARIO = Mappers.getMapper(UsuarioMapper.class);
    @Mapping(target = "roles", ignore = true)
    Usuario toUsuario(RegistrarUsuarioDTO registrarUsuarioDTO);
    @Mapping(target = "roles", ignore = true)
    UsuarioDTO toUsuarioDTO(Usuario usuario);
    @Mapping(target = "roles", ignore = true)
    LoginResponseDTO toLoginResponseDTO(Usuario usuario);

    @Mapping(target = "roles", ignore = true)
    Usuario usuarioDTOtoUsuario(UsuarioDTO usuarioDTO);
}
