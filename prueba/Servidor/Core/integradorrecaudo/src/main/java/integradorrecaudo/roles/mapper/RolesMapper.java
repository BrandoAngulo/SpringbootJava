package integradorrecaudo.roles.mapper;

import integradorrecaudo.roles.dto.RolesModulosAsignadosDTO;
import integradorrecaudo.roles.dto.RolesResponseDTO;
import integradorrecaudo.roles.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolesMapper {

    RolesMapper ROLES = Mappers.getMapper(RolesMapper.class);

    RolesResponseDTO toRolesResponseDTO(Roles roles);

    RolesModulosAsignadosDTO toRolesModulosAsignadosDTO(Roles roles);
}
