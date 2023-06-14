package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.CodeArticle;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.CodeArticleRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeArticleService {
    private final CodeArticleRepository codeArticleRepository;

    public CodeArticleService(CodeArticleRepository codeArticleRepository) {
        this.codeArticleRepository = codeArticleRepository;
    }
    public List<CodeArticle> findAllCodeArticle(){
        return codeArticleRepository.findAll();
    }

    public CodeArticle addCodeArticle(CodeArticle codeArticle) {
        return codeArticleRepository.save(codeArticle);
    }
}
