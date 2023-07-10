package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.model.Fabricant;
import com.example.BackSpringBoot.model.SiteFctSys;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.repository.SiteFctSysRepository;
import com.example.BackSpringBoot.service.FabricantService;
import com.example.BackSpringBoot.service.SiteFctSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/sitefctsys")
public class SiteFctSysController {
    private  final SiteFctSysService siteFctSysService;
    @Autowired
    private SiteFctSysRepository siteFctSysRepository;

    public SiteFctSysController(SiteFctSysService siteFctSysService) {
        this.siteFctSysService = siteFctSysService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<SiteFctSys>> getAllSiteFctSys(){
        List<SiteFctSys> sitefctsyss = siteFctSysRepository.findAll();
        return new ResponseEntity<>(sitefctsyss, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SiteFctSys> getSiteFctSys(@PathVariable long id){
        SiteFctSys siteFctSys = siteFctSysRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Site fct sys not exist with id: " + id));
        return new ResponseEntity<>(siteFctSys, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<SiteFctSys> addSiteFctSys(@RequestBody SiteFctSys siteFctSys){
        SiteFctSys newsitefctsys = siteFctSysService.addSiteFctSys(siteFctSys);
        return new ResponseEntity<>(newsitefctsys, HttpStatus.CREATED) ;
    }
}
