package com.qualitysales.posinventory.mapper;

import com.qualitysales.posinventory.Controllers.DTO.ClientDTO;
import com.qualitysales.posinventory.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientMapper MAPPER = Mappers.getMapper(ClientMapper.class);

    Client toClientDTO(ClientDTO clientDTO);
    ClientDTO toClient(Client client);

    List<Client> toClientsDTO(List<ClientDTO> clientDTO);
    List<ClientDTO> toClients(List<Client> client);

}
