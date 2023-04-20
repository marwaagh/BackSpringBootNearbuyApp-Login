package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.FamilleComposant;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.FamilleComposantRepository;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.FamilleComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/famillecomposant")
public class FamilleComposantController {

    private  final FamilleComposantService familleComposantService;
    @Autowired
    private FamilleComposantRepository familleComposantRepository;

    public FamilleComposantController(FamilleComposantService familleComposantService){ this.familleComposantService = familleComposantService; }
    @GetMapping("/all")
    public ResponseEntity<List<FamilleComposant>> getAllFamillesComposants(){
        List<FamilleComposant> famillescomposants = familleComposantRepository.findAll();
        return new ResponseEntity<>(famillescomposants, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<FamilleComposant> addFamilleComposants(@RequestBody FamilleComposant familleComposant){
        FamilleComposant newFamilleComposant = familleComposantService.addFamilleComposant(familleComposant);
        return new ResponseEntity<>(newFamilleComposant, HttpStatus.CREATED) ;
    }
}
