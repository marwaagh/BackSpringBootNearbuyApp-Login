package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.LigneCommandeClient;
import com.example.BackSpringBoot.repository.LigneCommandeClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCommandeClientService {
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    public LigneCommandeClientService(LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }
    public List<LigneCommandeClient> findAllLigneCommandeClient(){
        return ligneCommandeClientRepository.findAll();
    }

    public LigneCommandeClient addLigneCommandeClient(LigneCommandeClient ligneCommandeClient) {
        return ligneCommandeClientRepository.save(ligneCommandeClient);
    }

}
