package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.model.Nomenclature;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.repository.NomenclatureRepository;
import com.example.BackSpringBoot.service.FabricantService;
import com.example.BackSpringBoot.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/nomenclature")
public class NomenclatureController {
    private  final NomenclatureService nomenclatureService;
    @Autowired
    private NomenclatureRepository nomenclatureRepository;
    public NomenclatureController(NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Nomenclature>> getAllnomenclatures(){
        List<Nomenclature> nomenclatures = nomenclatureRepository.findAll();
        return new ResponseEntity<>(nomenclatures, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Nomenclature> addNomenclatures(@RequestBody Nomenclature nomenclature){
        Nomenclature newnomenclature = nomenclatureService.addNomenclature(nomenclature);
        return new ResponseEntity<>(nomenclature, HttpStatus.CREATED) ;
    }

    @GetMapping("/findbyclst/{clientSiteId}")
    public List<Nomenclature> getNomenclaturesByClientSiteId(@PathVariable Long clientSiteId) {
        return nomenclatureService.getNomenclaturesByClientSiteId(clientSiteId);
    }

}
