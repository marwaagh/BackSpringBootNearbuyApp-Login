package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import com.example.BackSpringBoot.service.DossierEquivalenceService;
import com.example.BackSpringBoot.service.DossierHomologationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/ipersyst/dossierhomologation")
public class DossierHomologationController {
    private  final DossierHomologationService dossierHomologationService;
    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DossierHomologationController(DossierHomologationService dossierHomologationService) {
        this.dossierHomologationService = dossierHomologationService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DossierHomologation>> getAllDSHs(){
        List<DossierHomologation> dshs = dossierHomologationService.findallDossierHomologations();
        return new ResponseEntity<>(dshs, HttpStatus.OK) ;
    }
/*
    @GetMapping("/equiv/{idinit}")
    @ResponseBody
    public ResponseEntity<List<DossierHomologation>>  getArticlesserie(@PathVariable Long idinit) {
        String sql = "SELECT * " +
                "FROM public.dossierequivalence " +
                "INNER JOIN public.article a1 ON dossierequivalence.id_art_equiv = a1.id " +
                "INNER JOIN public.article a2 ON dossierequivalence.id_art_init = a2.id " +
                "WHERE dossierequivalence.id_art_init = ?";
        Object[] params = { idinit };
        List<Map<String, Object>> deqsData = jdbcTemplate.queryForList(sql, params);
        List<DossierEquivalence> deqs = new ArrayList<>();

        for (Map<String, Object> deqData : deqsData) {
            DossierEquivalence deq = new DossierEquivalence();
            deq.setId((Long) deqData.get("id"));
            deq.setTs((Timestamp) deqData.get("ts"));
            deq.setDsequivCommentairesDemandeur((String) deqData.get("dsequiv_commentaires_demandeur"));
            deq.setDsequivCommentairesValidateur((String) deqData.get("dsequiv_commentaires_validateur"));
            deq.setDsequivReference((String) deqData.get("dsequiv_reference"));
            deq.setDsequivNiveauValidation((String) deqData.get("dsequiv_niveau_validation"));
            //article.setArtCouleur((String) articleData.get("art_couleur"));
            //article.setArtDateCouleur((Date) articleData.get("art_date_couleur"));
            //article.setArtCouleurPrecedente((String) articleData.get("art_couleur_precedente"));
            //article.setArtDateCouleurPrecedente((Date) articleData.get("art_date_couleur_precedente"));
            //article.setArtSource((String) articleData.get("art_source_changement_couleur"));
            //article.setArtLienDocAvisObsolescence((String) articleData.get("art_lien_doc_avis_obsolescence"));
            Long artEquivId = (Long) deqData.get("id_art_equiv");
            Article article = articleRepository.findById(artEquivId).orElse(null);
            deq.setPkArticleEquivalent(article);
            Long artInitId = (Long) deqData.get("id_art_init");
            Article articlee = articleRepository.findById(artInitId).orElse(null);
            deq.setPkArticleInitial(articlee);

            deqs.add(deq);
        }

        ResponseEntity<List<DossierEquivalence>> responseEntity = new ResponseEntity<>(deqs, HttpStatus.OK);
        return responseEntity;
    }
*/
    @PostMapping("/add")
    public ResponseEntity<DossierHomologation> addDSH(@RequestBody DossierHomologation dossierHomologation){
        DossierHomologation newDSH = dossierHomologationService.addDossierHomologation(dossierHomologation);
        return new ResponseEntity<>(newDSH, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DossierHomologation> updateDSH(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation){
        DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DSH not exist with id: " + id));
        //updateDEQ.setDsequivCouleurArtInit(dossierEquivalence.getDsequivCouleurArtInit());
        updateDSH.setDshReference(dossierHomologation.getDshReference());
        updateDSH.setAppOwner(dossierHomologation.getAppOwner());
        updateDSH.setDshNiveauValidation(dossierHomologation.getDshNiveauValidation());
        updateDSH.setDshDemandeRex(dossierHomologation.getDshDemandeRex());
        //updateDEQ.setDsequivDemandeurUser(dossierEquivalence.getDsequivDemandeurUser());
        //updateDEQ.setDsequivValidateurUser(dossierEquivalence.getDsequivValidateurUser());
        // updateDEQ.setDsequivCommentairesDemandeur(dossierEquivalence.getDsequivCommentairesDemandeur());
        // updateDEQ.setDsequivCommentairesValidateur(dossierEquivalence.getDsequivCommentairesValidateur());

        dossierHomologationRepository.save(updateDSH) ;
        return new ResponseEntity<>(updateDSH, HttpStatus.OK) ;
        // return "user updated !!!!";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDSH(@PathVariable("id") long id){
        DossierHomologation deleteDSH = dossierHomologationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));;
        dossierHomologationRepository.delete(deleteDSH);
        dossierHomologationService.deleteDossierHomologation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
