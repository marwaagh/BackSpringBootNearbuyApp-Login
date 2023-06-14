package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.CommandeClient;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.CommandeClientRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.service.CommandeClientService;
import com.example.BackSpringBoot.service.FabricantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/commandeclient")
public class CommandeClientController {
    private  final CommandeClientService commandeClientService;
    @Autowired
    private CommandeClientRepository commandeClientRepository;

    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommandeClient>> getAllCommandeClients(){
        List<CommandeClient> commandeClients = commandeClientRepository.findAll();
        return new ResponseEntity<>(commandeClients, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<CommandeClient> addCommandeClients(@RequestBody CommandeClient commandeClient){
        CommandeClient newCommandeClient = commandeClientService.addCommandeClient(commandeClient);
        return new ResponseEntity<>(newCommandeClient, HttpStatus.CREATED) ;
    }
}
