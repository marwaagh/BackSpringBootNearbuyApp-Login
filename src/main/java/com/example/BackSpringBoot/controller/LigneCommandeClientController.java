package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.LigneCommandeClient;
import com.example.BackSpringBoot.repository.LigneCommandeClientRepository;
import com.example.BackSpringBoot.service.LigneCommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/ligneCommandeClient")
public class LigneCommandeClientController {
    private  final LigneCommandeClientService ligneCommandeClientService;
    @Autowired
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    public LigneCommandeClientController(LigneCommandeClientService ligneCommandeClientService) {
        this.ligneCommandeClientService = ligneCommandeClientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LigneCommandeClient>> getAllLigneCommandeClients(){
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAll();
        return new ResponseEntity<>(ligneCommandeClients, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<LigneCommandeClient> addLigneCommandeClients(@RequestBody LigneCommandeClient ligneCommandeClient){
        LigneCommandeClient newLigneCommandeClient = ligneCommandeClientService.addLigneCommandeClient(ligneCommandeClient);
        return new ResponseEntity<>(newLigneCommandeClient, HttpStatus.CREATED) ;
    }
}
