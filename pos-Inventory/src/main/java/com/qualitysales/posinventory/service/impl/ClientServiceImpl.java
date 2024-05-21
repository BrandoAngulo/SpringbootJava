package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.ClientDTO;
import com.qualitysales.posinventory.mapper.ClientMapper;
import com.qualitysales.posinventory.model.Client;
import com.qualitysales.posinventory.repository.ClientRepository;
import com.qualitysales.posinventory.service.ClientService;
import com.qualitysales.posinventory.utils.HttpClientUtil;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final HttpClientUtil httpClientUtil;

    @Override
    public ClientDTO getClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client ID"));
        ClientDTO clientDTO = ClientMapper.MAPPER.toClient(client);

        try {
            log.info("getClient ok: {}", clientDTO);
            return clientDTO;
        } catch (Exception e) {
            log.error("getClient Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOS = ClientMapper.MAPPER.toClients(clients);

        try {
            log.info("getAllClients ok: {}", clientDTOS);
            return clientDTOS;
        } catch (Exception e) {
            log.error("getAllClients Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<ClientDTO> getClientByName(String firstName, String lastName) {
        List<Client> clients = clientRepository.findByNameOrLastName(firstName, lastName);
        List<ClientDTO> clientDTOS = ClientMapper.MAPPER.toClients(clients);
        if (clientDTOS.isEmpty()) {
            log.error("getClientByName Error no client exist {}", clientDTOS);
            throw new IllegalArgumentException("No Client found with name or lastname = " + firstName + " " + lastName);
        }
        try {
            log.info("getClientByName ok: {}", clientDTOS);
            return clientDTOS;
        } catch (Exception e) {
            log.error("getClientByName Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ClientDTO addClient(Client client) {
        try {
            ClientDTO clientDTO = ClientMapper.MAPPER.toClient(client);
            clientRepository.save(client);
            log.info("addClient ok: {}", client);
            return clientDTO;
        } catch (Exception e) {
            log.error("addClient Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ClientDTO updateClient(Integer id, Client client) {
        Client idClient = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client ID"));
        ClientDTO clientDTO = ClientMapper.MAPPER.toClient(idClient);
        try {

            if (clientDTO.getId() == id) {
                clientDTO.setLastName(client.getLastName());
                clientDTO.setDocument(client.getDocument());
                clientDTO.setCity(client.getCity());
                clientDTO.setResidence(client.getResidence());
                clientDTO.setCellPhone(client.getCellPhone());
                clientDTO.setEmail(client.getEmail());
                clientDTO.setEstate(client.getEstate());
                clientRepository.save(idClient);
                log.info("updateClient ok: {}", idClient);
            }
            return clientDTO;

        } catch (Exception e) {
            log.error("updateClient Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client ID"));
        try {
            log.info("deleteClient ok: {}", client);
            clientRepository.deleteById(client.getId());
        } catch (Exception e) {
            log.error("deleteClient Error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }

    }

}
