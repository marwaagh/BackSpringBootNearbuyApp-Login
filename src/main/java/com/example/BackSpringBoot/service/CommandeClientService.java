package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.CommandeClient;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.CommandeClientRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeClientService {
    private final CommandeClientRepository commandeClientRepository;

    public CommandeClientService(CommandeClientRepository commandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
    }
    public List<CommandeClient> findAllCommandeClient(){
        return commandeClientRepository.findAll();
    }

    public CommandeClient addCommandeClient(CommandeClient commandeClient) {
        return commandeClientRepository.save(commandeClient);
    }
}
