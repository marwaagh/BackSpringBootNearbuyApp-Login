package com.example.BackSpringBoot.controller;


import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.repository.ClientRepository;
import com.example.BackSpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/client")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> getClient(@PathVariable long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));
        return new ResponseEntity<>(client, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateSite(@PathVariable long id, @RequestBody Client client){
        Client updateClient = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));
        updateClient.setCltReference(client.getCltReference());
        updateClient.setCltNom(client.getCltNom());
        updateClient.setCltCodeMovex(client.getCltCodeMovex());
        updateClient.setCltNomContact(client.getCltNomContact());
        updateClient.setCltWebsite(client.getCltWebsite());
        updateClient.setCltTel(client.getCltTel());
        updateClient.setCltFax(client.getCltFax());
        updateClient.setCltAdresse(client.getCltAdresse());
        updateClient.setCltCpVille(client.getCltCpVille());
        updateClient.setCltEmail(client.getCltEmail());
        updateClient.setCltLogoPath(client.getCltLogoPath());
        clientRepository.save(updateClient) ;
        return new ResponseEntity<>(updateClient, HttpStatus.OK) ;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id){
        Client deleteClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));
        clientRepository.delete(deleteClient);
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
