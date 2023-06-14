package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.Livraison;
import com.example.BackSpringBoot.repository.LivraisonRepository;
import com.example.BackSpringBoot.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/livraison")
public class LivraisonController {
    private  final LivraisonService livraisonService;
    @Autowired
    private LivraisonRepository livraisonRepository;

    public LivraisonController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Livraison>> getAllLivraisons(){
        List<Livraison> livraisons = livraisonRepository.findAll();
        return new ResponseEntity<>(livraisons, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Livraison> addLivraisons(@RequestBody Livraison livraison){
        Livraison newLivraison = livraisonService.addLivraison(livraison);
        return new ResponseEntity<>(newLivraison, HttpStatus.CREATED) ;
    }
}
