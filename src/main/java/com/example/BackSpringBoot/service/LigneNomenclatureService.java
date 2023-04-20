package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.LigneNomenclature;
import com.example.BackSpringBoot.repository.LigneNomenclatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigneNomenclatureService {
    private final LigneNomenclatureRepository ligneNomenclatureRepository;

    public LigneNomenclatureService(LigneNomenclatureRepository ligneNomenclatureRepository) {
        this.ligneNomenclatureRepository = ligneNomenclatureRepository;
    }

    public List<LigneNomenclature> findAllLigneNomenclature(){
        return ligneNomenclatureRepository.findAll();
    }

    public LigneNomenclature addLigneNomenclature(LigneNomenclature ligneNomenclature) {
        return ligneNomenclatureRepository.save(ligneNomenclature);
    }
}
