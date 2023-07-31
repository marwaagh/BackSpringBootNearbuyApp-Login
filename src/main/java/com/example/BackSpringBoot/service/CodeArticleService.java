package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.CodeArticle;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import com.example.BackSpringBoot.repository.CodeArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeArticleService {
    private final CodeArticleRepository codeArticleRepository;
    private  final ClientSiteRepository clientSiteRepository;

    public CodeArticleService(CodeArticleRepository codeArticleRepository, ClientSiteRepository clientSiteRepository) {
        this.codeArticleRepository = codeArticleRepository;
        this.clientSiteRepository = clientSiteRepository;
    }
    public List<CodeArticle> findAllCodeArticle(){
        return codeArticleRepository.findAll();
    }

    public CodeArticle addCodeArticle(CodeArticle codeArticle) {
        return codeArticleRepository.save(codeArticle);
    }

    public List<CodeArticle> getCodeArticlesByClientSiteId(Long clientSiteId) {
        // Fetch the ClientSite object based on the provided clientSiteId
        // You should have a method in the ClientSiteRepository to fetch a ClientSite by ID.
        ClientSite clientSite = clientSiteRepository.findById(clientSiteId)
                .orElseThrow(() -> new RuntimeException("ClientSite not found with ID: " + clientSiteId));

        // Use the repository method to find all AppUsers associated with the ClientSite
        return codeArticleRepository.findAllByPkClientSite(clientSite);
    }
}
