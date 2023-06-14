package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientSiteService {
    private final ClientSiteRepository clientSiteRepository;

    public ClientSiteService(ClientSiteRepository clientSiteRepository) {
        this.clientSiteRepository = clientSiteRepository;
    }

    public List<ClientSite> findAll(){
        return clientSiteRepository.findAll();
    }

    public ClientSite addClientSite(ClientSite clientSite) {
        return clientSiteRepository.save(clientSite);
    }

    public void deleteClientSite(long id) {
        clientSiteRepository.deleteClientSiteById(id);
    }
}
