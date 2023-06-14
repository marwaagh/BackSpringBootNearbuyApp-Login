package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.Files.ExcelUtils;
import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import com.example.BackSpringBoot.service.DossierEquivalenceService;
import com.example.BackSpringBoot.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/ipersyst/dossierequivalence")
public class DossierEquivalenceController {
    private final DossierEquivalenceService dossierEquivalenceService;
    @Autowired
    private DossierEquivalenceRepository dossierEquivalenceRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReportService reportService;

    public DossierEquivalenceController(DossierEquivalenceService dossierEquivalenceService) {
        this.dossierEquivalenceService = dossierEquivalenceService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DossierEquivalence>> getAllDEQs() {
        List<DossierEquivalence> deqs = dossierEquivalenceService.findallDossierEquivalences();
        return new ResponseEntity<>(deqs, HttpStatus.OK);
    }

    @GetMapping("/equiv/{idinit}")
    @ResponseBody
    public ResponseEntity<List<DossierEquivalence>> getArticlesserie(@PathVariable Long idinit) {
        String sql = "SELECT * " +
                "FROM public.dossierequivalence " +
                "INNER JOIN public.article a1 ON dossierequivalence.id_art_equiv = a1.id " +
                "INNER JOIN public.article a2 ON dossierequivalence.id_art_init = a2.id " +
                "WHERE dossierequivalence.id_art_init = ?";
        Object[] params = {idinit};
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

    @PostMapping("/add")
    public ResponseEntity<DossierEquivalence> addDEQ(@RequestBody DossierEquivalence dossierEquivalence) {
        DossierEquivalence newDEQ = dossierEquivalenceService.addDossierEquivalence(dossierEquivalence);
        return new ResponseEntity<>(newDEQ, HttpStatus.CREATED);
    }
    //update from excel file
    @PostMapping("/addFromExcel")
    public ResponseEntity<List<DossierEquivalence>> uploadDeqData(@RequestParam("file") MultipartFile file) throws IOException {
        List<DossierEquivalence> deqs = dossierEquivalenceService.addDossierEquivalencesFromExcelFile(file);
        return new ResponseEntity<>(deqs, HttpStatus.CREATED);
    }
//list of deqs to excel file
    @GetMapping("/exporttoexcel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        String fileName = "deqs.xlsx";
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);

        List<DossierEquivalence> deqs = dossierEquivalenceRepository.findAll();
        ByteArrayInputStream inputStream = ExcelUtils.deqsToExcel(deqs);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @GetMapping("/report/pdf")
    public ResponseEntity<byte[]> generateReport() throws IOException, JRException {
        return reportService.exportRapport() ;
    }

    @GetMapping("/report/pdf/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportDeq(id);
    }

    @GetMapping("/report/arboascinit/{id}")
    public ResponseEntity<byte[]> generateReportArboAscinit(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportArboAscInit(id);
    }

    @GetMapping("/report/arboasceq/{id}")
    public ResponseEntity<byte[]> generateReportArboAsceq(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportArboAscEq(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DossierEquivalence> updateDEQ(@PathVariable long id, @RequestBody DossierEquivalence dossierEquivalence) {
        DossierEquivalence updateDEQ = dossierEquivalenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        //updateDEQ.setDsequivCouleurArtInit(dossierEquivalence.getDsequivCouleurArtInit());
        updateDEQ.setDsequivReference(dossierEquivalence.getDsequivReference());
        updateDEQ.setDsequivNiveauValidation(dossierEquivalence.getDsequivNiveauValidation());
        updateDEQ.setDsequivDateNiveauValidation(dossierEquivalence.getDsequivDateNiveauValidation());
        updateDEQ.setDsequivDateCreation(dossierEquivalence.getDsequivDateCreation());
        //updateDEQ.setDsequivDemandeurUser(dossierEquivalence.getDsequivDemandeurUser());
        //updateDEQ.setDsequivValidateurUser(dossierEquivalence.getDsequivValidateurUser());
        // updateDEQ.setDsequivCommentairesDemandeur(dossierEquivalence.getDsequivCommentairesDemandeur());
        // updateDEQ.setDsequivCommentairesValidateur(dossierEquivalence.getDsequivCommentairesValidateur());

        dossierEquivalenceRepository.save(updateDEQ);
        return new ResponseEntity<>(updateDEQ, HttpStatus.OK);
        // return "user updated !!!!";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDEQ(@PathVariable("id") long id) {
        DossierEquivalence deleteDEQ = dossierEquivalenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        ;
        dossierEquivalenceRepository.delete(deleteDEQ);
        dossierEquivalenceService.deleteDossierEquivalence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public boolean isEquiv1970(DossierEquivalence dossierEquivalence) {
        boolean isEquiv1970 = false;
        Article artEquiv = dossierEquivalence.getPkArticleEquivalent();
        if (artEquiv != null) {
            Date dateMaj = artEquiv.getArtDateCouleur();
            SimpleDateFormat dtf = new SimpleDateFormat("yyyy");
            if ("1970".equals(dtf.format(dateMaj))) {
                isEquiv1970 = true;
            }
        }
        return isEquiv1970;
    }

    @PutMapping("/attenteval/{id}")
    public ResponseEntity<DossierEquivalence> doActionMiseEnAttente(@PathVariable long id, @RequestBody DossierEquivalence dossierEquivalence) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierEquivalence updateDEQ = dossierEquivalenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        if (isEquiv1970(dossierEquivalence)) {
            return new ResponseEntity<>(updateDEQ, HttpStatus.valueOf("date equals to 1970"));
        }
        else {
        updateDEQ.setDsequivNiveauValidation("En attente validation");
        updateDEQ.setDsequivDateNiveauValidation(sqlDate);
        updateDEQ.setDsequivDateDemandeur(sqlDate);
        updateDEQ.setDsequivDemandeurUser(dossierEquivalence.getDsequivDemandeurUser()); //Demandeur
        updateDEQ.setDsequivCommentairesDemandeur(dossierEquivalence.getDsequivCommentairesDemandeur()); //comments demandeur
        dossierEquivalenceRepository.save(updateDEQ);
        return new ResponseEntity<>(updateDEQ, HttpStatus.OK);

        }
    }
    @PutMapping("/validersans/{id}")
    public ResponseEntity<DossierEquivalence> doActionValiderSans(@PathVariable long id, @RequestBody DossierEquivalence dossierEquivalence) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierEquivalence updateDEQ = dossierEquivalenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        if (isEquiv1970(dossierEquivalence)) {
            return new ResponseEntity<>(updateDEQ, HttpStatus.valueOf("date equals to 1970"));
        }
        else {
            updateDEQ.setDsequivNiveauValidation("Validé sans réserve");
            updateDEQ.setDsequivDateNiveauValidation(sqlDate);
            updateDEQ.setDsequivDateValidateur(sqlDate);
            updateDEQ.setDsequivValidateurUser(dossierEquivalence.getDsequivValidateurUser()); //Demandeur
            updateDEQ.setDsequivCommentairesValidateur(dossierEquivalence.getDsequivCommentairesValidateur()); //comments demandeur
            dossierEquivalenceRepository.save(updateDEQ);
            return new ResponseEntity<>(updateDEQ, HttpStatus.OK);

        }
    }

    @PutMapping("/valideravec/{id}")
    public ResponseEntity<DossierEquivalence> doActionValiderAvec(@PathVariable long id, @RequestBody DossierEquivalence dossierEquivalence) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierEquivalence updateDEQ = dossierEquivalenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        if (isEquiv1970(dossierEquivalence)) {
            return new ResponseEntity<>(updateDEQ, HttpStatus.valueOf("date equals to 1970"));
        }
        else {
            updateDEQ.setDsequivNiveauValidation("Validé avec réserve");
            updateDEQ.setDsequivDateNiveauValidation(sqlDate);
            updateDEQ.setDsequivDateValidateur(sqlDate);
            updateDEQ.setDsequivValidateurUser(dossierEquivalence.getDsequivValidateurUser()); //Demandeur
            updateDEQ.setDsequivCommentairesValidateur(dossierEquivalence.getDsequivCommentairesValidateur()); //comments demandeur
            dossierEquivalenceRepository.save(updateDEQ);
            return new ResponseEntity<>(updateDEQ, HttpStatus.OK);

        }
    }

    @PutMapping("/refuser/{id}")
    public ResponseEntity<DossierEquivalence> doActionRefuser(@PathVariable long id, @RequestBody DossierEquivalence dossierEquivalence) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierEquivalence updateDEQ = dossierEquivalenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        if (isEquiv1970(dossierEquivalence)) {
            return new ResponseEntity<>(updateDEQ, HttpStatus.valueOf("date equals to 1970"));
        }
        else {
            updateDEQ.setDsequivNiveauValidation("Refusé");
            updateDEQ.setDsequivDateNiveauValidation(sqlDate);
            updateDEQ.setDsequivDateValidateur(sqlDate);
            updateDEQ.setDsequivValidateurUser(dossierEquivalence.getDsequivValidateurUser()); //Demandeur
            updateDEQ.setDsequivCommentairesValidateur(dossierEquivalence.getDsequivCommentairesValidateur()); //comments demandeur
            dossierEquivalenceRepository.save(updateDEQ);
            return new ResponseEntity<>(updateDEQ, HttpStatus.OK);

        }
    }
}