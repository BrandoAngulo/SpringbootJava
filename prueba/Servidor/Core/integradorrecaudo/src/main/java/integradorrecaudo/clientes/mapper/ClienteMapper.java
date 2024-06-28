package integradorrecaudo.clientes.mapper;

import integradorrecaudo.clientes.dto.ClienteDTO;
import integradorrecaudo.clientes.dto.RegistrarActualizarClienteDTO;
import integradorrecaudo.clientes.entity.Cliente;
import integradorrecaudo.servidores.entity.Servidor;
import integradorrecaudo.servidores.mapper.ServidorMapper;
import integradorrecaudo.versiones.entity.Versiones;
import integradorrecaudo.versiones.mapper.VersionesMapper;
import integradorrecaudo.zona_horaria.entity.ZonaHoraria;
import integradorrecaudo.zona_horaria.mapper.ZonaHorariaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ServidorMapper.class, ZonaHorariaMapper.class, VersionesMapper.class,})
public interface ClienteMapper {

    ClienteMapper CLIENTE = Mappers.getMapper(ClienteMapper.class);


    @Mapping(target = "servidor", expression = "java(integerToServidor(registrarActualizarClienteDTO.servidor()))")
    @Mapping(target = "zonaHoraria", expression = "java(integerToZonaHoraria(registrarActualizarClienteDTO.zonaHoraria()))")
    @Mapping(target = "version", expression = "java(integerToVersiones(registrarActualizarClienteDTO.version()))")
    Cliente registrarActualizarClienteDTOToCliente(RegistrarActualizarClienteDTO registrarActualizarClienteDTO);

    @Mapping(target = "servidor", expression = "java(servidorToString(cliente))")
    @Mapping(target = "zonaHoraria", expression = "java(zonaHorariaToString(cliente))")
    @Mapping(target = "version", expression = "java(versionesToString(cliente))")
    ClienteDTO toClienteDTO(Cliente cliente);

    Cliente toCliente(ClienteDTO clienteDTO);


    default Servidor integerToServidor(Integer idServidor) {

        Servidor servidor = new Servidor();
        servidor.setId(idServidor);
        return servidor;
    }

    default ZonaHoraria integerToZonaHoraria(Integer idZonaHoraria) {

        ZonaHoraria zonaHoraria = new ZonaHoraria();
        zonaHoraria.setId(idZonaHoraria);
        return zonaHoraria;
    }

    default Versiones integerToVersiones(Integer idVersiones) {

        Versiones versiones = new Versiones();
        versiones.setId(idVersiones);
        return versiones;
    }

    default String servidorToString(Cliente cliente) {
        return cliente.getServidor().getNombre();
    }

    default String zonaHorariaToString(Cliente cliente) {
        return cliente.getZonaHoraria().getDescripcion();
    }

    default String versionesToString(Cliente cliente) {
        return cliente.getVersion().getVersion();
    }
}
