package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.Article;
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

    public BonTravailTest findById(Long id){
        return bonTravailTestRepository.findById(id).orElseThrow(()-> new UserNotFoundException("le bdt ayant l id" + id +"est introuvable")) ;
    }

}
