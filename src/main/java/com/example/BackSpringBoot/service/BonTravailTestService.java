package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.BonTravailTest;
import com.example.BackSpringBoot.repository.BonTravailTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BonTravailTestService {

    private final BonTravailTestRepository bonTravailTestRepository;

    public BonTravailTestService(BonTravailTestRepository bonTravailTestRepository) {
        this.bonTravailTestRepository = bonTravailTestRepository;
    }
    public List<BonTravailTest> findAll(){
        return bonTravailTestRepository.findAll();
    }

    public BonTravailTest addBonTravailTest(BonTravailTest bonTravailTest) {
        return bonTravailTestRepository.save(bonTravailTest);
    }

}
