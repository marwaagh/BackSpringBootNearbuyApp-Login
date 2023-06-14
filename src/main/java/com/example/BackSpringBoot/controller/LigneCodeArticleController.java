package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.LigneCodeArticle;
import com.example.BackSpringBoot.repository.LigneCodeArticleRepository;
import com.example.BackSpringBoot.service.LigneCodeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/lignecodearticle")
public class LigneCodeArticleController {
    private  final LigneCodeArticleService ligneCodeArticleService;
    @Autowired
    private LigneCodeArticleRepository ligneCodeArticleRepository;

    public LigneCodeArticleController(LigneCodeArticleService ligneCodeArticleService) {
        this.ligneCodeArticleService = ligneCodeArticleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LigneCodeArticle>> getAllLigneCodeArticles(){
        List<LigneCodeArticle> ligneCodeArticles = ligneCodeArticleRepository.findAll();
        return new ResponseEntity<>(ligneCodeArticles, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<LigneCodeArticle> addLigneCodeArticles(@RequestBody LigneCodeArticle ligneCodeArticle){
        LigneCodeArticle newLigneCodeArticle = ligneCodeArticleService.addLigneCodeArticle(ligneCodeArticle);
        return new ResponseEntity<>(newLigneCodeArticle, HttpStatus.CREATED) ;
    }
}
