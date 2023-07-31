package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(long id) {
        clientRepository.deleteClientById(id);
    }

    public Optional<Client> getClientByClientSiteId(Long clientSiteId) {
        return clientRepository.findClientByClientSiteId(clientSiteId);
    }

}
