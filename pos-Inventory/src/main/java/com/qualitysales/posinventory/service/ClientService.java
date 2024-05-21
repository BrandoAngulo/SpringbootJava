package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.ClientDTO;
import com.qualitysales.posinventory.model.Client;
import com.qualitysales.posinventory.utils.HttpClientUtil;

import java.util.List;

public interface ClientService {
    ClientDTO getClient(Integer id);
    List<ClientDTO> getAllClients();
    List<ClientDTO> getClientByName(String firstName, String lastName);
    ClientDTO addClient(Client client);
    ClientDTO updateClient(Integer id, Client client);
    void deleteClient(Integer id);
}
