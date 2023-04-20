package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Nomenclature;
import com.example.BackSpringBoot.repository.NomenclatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NomenclatureService {
    private final NomenclatureRepository nomenclatureRepository;

    public NomenclatureService(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    public List<Nomenclature> findAllNomenclature(){
        return nomenclatureRepository.findAll();
    }

    public Nomenclature addNomenclature(Nomenclature nomenclature) {
        return nomenclatureRepository.save(nomenclature);
    }
}
