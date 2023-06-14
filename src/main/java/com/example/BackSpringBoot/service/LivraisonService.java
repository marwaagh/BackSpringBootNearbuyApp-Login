package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Livraison;
import com.example.BackSpringBoot.repository.LivraisonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonService {
    private final LivraisonRepository livraisonRepository;

    public LivraisonService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }
    public List<Livraison> findAllLivraison(){
        return livraisonRepository.findAll();
    }

    public Livraison addLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

}
