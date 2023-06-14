package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Site;
import com.example.BackSpringBoot.repository.SiteRepository;
import com.example.BackSpringBoot.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/site")
public class SiteController {

    private final SiteService siteService;
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Site>> getAllSites(){
        List<Site> sites = siteService.findAll();
        return new ResponseEntity<>(sites, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Site> getSite(@PathVariable long id){
        Site site = siteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("site not exist with id: " + id));
        return new ResponseEntity<>(site, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Site> addSite(@RequestBody Site site){
        Site newSite = siteService.addSite(site);
        return new ResponseEntity<>(newSite, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable long id, @RequestBody Site site){
        Site updateSite = siteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Site not exist with id: " + id));
        updateSite.setSitReference(site.getSitReference());
        updateSite.setSitNom(site.getSitNom());
        siteRepository.save(updateSite) ;
        return new ResponseEntity<>(updateSite, HttpStatus.OK) ;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSite(@PathVariable("id") long id){
        Site deleteSite = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site not exist with id: " + id));
        siteRepository.delete(deleteSite);
        siteService.deleteSite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
