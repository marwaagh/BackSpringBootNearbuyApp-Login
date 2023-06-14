package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.LigneCodeArticle;
import com.example.BackSpringBoot.repository.LigneCodeArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneCodeArticleService {
    private final LigneCodeArticleRepository ligneCodeArticleRepository;

    public LigneCodeArticleService(LigneCodeArticleRepository ligneCodeArticleRepository) {
        this.ligneCodeArticleRepository = ligneCodeArticleRepository;
    }
    public List<LigneCodeArticle> findAllLigneCodeArticle(){
        return ligneCodeArticleRepository.findAll();
    }

    public LigneCodeArticle addLigneCodeArticle(LigneCodeArticle ligneCodeArticle) {
        return ligneCodeArticleRepository.save(ligneCodeArticle);
    }

}
