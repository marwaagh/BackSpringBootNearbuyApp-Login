package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.FamilleComposant;
import com.example.BackSpringBoot.repository.FamilleComposantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilleComposantService {

    private final FamilleComposantRepository familleComposantRepository;

    @Autowired
    public FamilleComposantService(FamilleComposantRepository familleComposantRepository) {
        this.familleComposantRepository = familleComposantRepository;
    }

    public List<FamilleComposant> findAllFamilleComposants(){
        return familleComposantRepository.findAll();
    }

    public FamilleComposant addFamilleComposant(FamilleComposant familleComposant) {
        return familleComposantRepository.save(familleComposant);
    }
}
