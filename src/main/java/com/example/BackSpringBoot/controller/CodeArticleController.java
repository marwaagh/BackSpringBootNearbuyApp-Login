package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.CodeArticle;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.CodeArticleRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.service.CodeArticleService;
import com.example.BackSpringBoot.service.FabricantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/codearticle")
public class CodeArticleController {
    private  final CodeArticleService codeArticleService;
    @Autowired
    private CodeArticleRepository codeArticleRepository;

    public CodeArticleController(CodeArticleService codeArticleService) {
        this.codeArticleService = codeArticleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CodeArticle>> getAllCodeArticles(){
        List<CodeArticle> codeArticles = codeArticleRepository.findAll();
        return new ResponseEntity<>(codeArticles, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<CodeArticle> addCodeArticles(@RequestBody CodeArticle codeArticle){
        CodeArticle newCodeArticle = codeArticleService.addCodeArticle(codeArticle);
        return new ResponseEntity<>(newCodeArticle, HttpStatus.CREATED) ;
    }
}
