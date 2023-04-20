package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.FabricantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricantService {

    private final FabricantRepository fabricantRepository;

    public FabricantService(FabricantRepository fabricantRepository) {
        this.fabricantRepository = fabricantRepository;
    }
    public List<Fabricant> findAllFabricant(){
        return fabricantRepository.findAll();
    }

    public Fabricant addFabricant(Fabricant fabricant) {
        return fabricantRepository.save(fabricant);
    }
}
