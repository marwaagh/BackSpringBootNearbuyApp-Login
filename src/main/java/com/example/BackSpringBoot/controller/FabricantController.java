package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.service.FabricantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/ipersyst/fabricant")
public class FabricantController {
    private  final FabricantService fabricantService;
    @Autowired
    private FabricantRepository fabricantRepository;

    public FabricantController(FabricantService fabricantService) {
        this.fabricantService = fabricantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fabricant>> getAllFabricants(){
        List<Fabricant> fabricants = fabricantRepository.findAll();
        return new ResponseEntity<>(fabricants, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Fabricant> getFabricant(@PathVariable long id){
        Fabricant fabricant = fabricantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("fabricant not exist with id: " + id));
        return new ResponseEntity<>(fabricant, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Fabricant> addFabricants(@RequestBody Fabricant fabricant){
        Fabricant newFabricant = fabricantService.addFabricant(fabricant);
        return new ResponseEntity<>(newFabricant, HttpStatus.CREATED) ;
    }
}
