package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.model.LigneNomenclature;
import com.example.BackSpringBoot.model.Nomenclature;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.repository.LigneNomenclatureRepository;
import com.example.BackSpringBoot.service.FabricantService;
import com.example.BackSpringBoot.service.LigneNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/lignenomenclature")
public class LigneNomenclatureController {
    private  final LigneNomenclatureService ligneNomenclatureService;
    @Autowired
    private LigneNomenclatureRepository ligneNomenclatureRepository;

    public LigneNomenclatureController(LigneNomenclatureService ligneNomenclatureService) {
        this.ligneNomenclatureService = ligneNomenclatureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LigneNomenclature>> getAllLignenomenclature(){
        List<LigneNomenclature> lignenomenclatures = ligneNomenclatureRepository.findAll();
        return new ResponseEntity<>(lignenomenclatures, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<LigneNomenclature> addLigneNomenclatures(@RequestBody LigneNomenclature ligneNomenclature){
        LigneNomenclature newlignenomenclature = ligneNomenclatureService.addLigneNomenclature(ligneNomenclature);
        return new ResponseEntity<>(newlignenomenclature, HttpStatus.CREATED) ;
    }
}
