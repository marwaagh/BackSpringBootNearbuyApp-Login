package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierHomologationService {

    private final DossierHomologationRepository dossierHomologationRepository;

    @Autowired
    public DossierHomologationService(DossierHomologationRepository dossierHomologationRepository) {
        this.dossierHomologationRepository = dossierHomologationRepository;
    }

    public DossierHomologation addDossierHomologation(DossierHomologation dossierHomologation){
        return dossierHomologationRepository.save(dossierHomologation);
    }
    public List<DossierHomologation> findallDossierHomologations(){
        return dossierHomologationRepository.findAll();
    }
    public DossierHomologation updateDossierHomologation(DossierHomologation dossierHomologation){
        return  dossierHomologationRepository.save(dossierHomologation) ;
    }
    public DossierHomologation findDossierHomologationById(Long id){
        return dossierHomologationRepository.findById(id).orElseThrow(()-> new UserNotFoundException("le dossier Homologation ayant l id" + id +"est introuvable")) ;
    }
    public void deleteDossierHomologation(Long id){
        dossierHomologationRepository.deleteDossierHomologationById(id);
    }
}
