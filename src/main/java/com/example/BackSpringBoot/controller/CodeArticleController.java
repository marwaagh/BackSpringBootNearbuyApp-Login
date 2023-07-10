package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.CodeArticle;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.CodeArticleRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.CodeArticleService;
import com.example.BackSpringBoot.service.FabricantService;
import org.springframework.beans.BeanUtils;
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

    @PutMapping("/update/{id}")
        public ResponseEntity<CodeArticle> updateCodeArticle(@PathVariable long id, @RequestBody CodeArticle codeArticle){
        CodeArticle existingArticle = codeArticleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("code article does not exist with id: " + id));

        // Copy only the provided properties while ignoring null or default values
        BeanUtils.copyProperties(codeArticle, existingArticle, ArticleService.getNullPropertyNames(codeArticle));

        CodeArticle updatedCodeArticle = codeArticleRepository.save(existingArticle);
        return new ResponseEntity<>(updatedCodeArticle, HttpStatus.OK);
    }
}
