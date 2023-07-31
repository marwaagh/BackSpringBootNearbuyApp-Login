package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Access;
import com.example.BackSpringBoot.repository.AccessRepository;
import com.example.BackSpringBoot.service.AccessService;
import com.example.BackSpringBoot.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/ipersyst/access")
public class AccessController {

    @Autowired
    private AccessRepository accessRepository;
    private AccessService accessService;
    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Access> addAccess(@RequestBody Access access){
        Access newAccess = accessService.addAccess(access);
        return new ResponseEntity<>(newAccess, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Access> updateAccess(@PathVariable long id, @RequestBody Access access){
        Access existingArticle = accessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("article does not exist with id: " + id));

        BeanUtils.copyProperties(access, existingArticle, ArticleService.getNullPropertyNames(access));

        Access updatedArticle = accessRepository.save(existingArticle);
        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }
}