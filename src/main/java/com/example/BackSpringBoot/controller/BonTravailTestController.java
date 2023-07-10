package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.*;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.BonTravailTestService;
import com.example.BackSpringBoot.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/ipersyst/bontravailtest")
public class BonTravailTestController {

    private  final BonTravailTestService bonTravailTestService;
    @Autowired
    private BonTravailTestRepository bonTravailTestRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;

    @Autowired
    private FabricantRepository fabricantRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReportService reportService;

    public BonTravailTestController(BonTravailTestService bonTravailTestService) {
        this.bonTravailTestService = bonTravailTestService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<BonTravailTest>> getAllBonTravailTest(){
        List<BonTravailTest> bonTravailTests = bonTravailTestRepository.findAll();
        return new ResponseEntity<>(bonTravailTests, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BonTravailTest> getArticleById(@PathVariable("id") Long id){
        BonTravailTest bonTravailTest = bonTravailTestService.findById(id);
        return new ResponseEntity<>(bonTravailTest, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<BonTravailTest> addBonTravailTest(@RequestBody BonTravailTest bonTravailTest){
        BonTravailTest newbontravailtest = bonTravailTestService.addBonTravailTest(bonTravailTest);
        return new ResponseEntity<>(newbontravailtest, HttpStatus.CREATED) ;
    }

    @GetMapping("/bdtpardsh/{iddsh}")
    @ResponseBody
    public ResponseEntity<List<BonTravailTest>> getBDTparDSH(@PathVariable Long iddsh) {
        String sql = "SELECT * FROM bon_travail_test bdt WHERE bdt.pk_dossier_homologation = ? ";
        Object[] params = {iddsh};
        List<Map<String, Object>> bdtsData = jdbcTemplate.queryForList(sql, params);
        List<BonTravailTest> bdts = new ArrayList<>();

        for (Map<String, Object> bdtData : bdtsData) {
            BonTravailTest bdt = new BonTravailTest();
            bdt.setId((Long) bdtData.get("id"));
            bdt.setTs((Timestamp) bdtData.get("ts"));
            bdt.setAppOwner((String) bdtData.get("app_owner"));
            bdt.setBdtCommentaireLabo((String) bdtData.get("bdt_commentaire_labo"));
            bdt.setBdtCommentaireValidateur((String) bdtData.get("bdt_commentaire_validateur"));
            bdt.setBdtDateCreation((Date) bdtData.get("bdt_date_creation"));
            bdt.setBdtDateEnvoi((Date) bdtData.get("bdt_date_envoi"));
            bdt.setBdtDateRetour((Date) bdtData.get("bdt_date_retour"));
            bdt.setBdtDateRetourSouhaitee((Date) bdtData.get("bdt_date_retour_souhaitee"));
            bdt.setBdtDemandeurDate((Date) bdtData.get("bdt_demandeur_date"));
            bdt.setBdtDemandeurUser((String) bdtData.get("bdt_demandeur_user"));
            bdt.setBdtEstTestFonctionnel((boolean) bdtData.get("bdt_est_test_fonctionnel"));
            bdt.setBdtNiveauValidation((String) bdtData.get("bdt_niveau_validation"));
            bdt.setBdtObservations((String) bdtData.get("bdt_observations"));
            bdt.setBdtQteTemp1((String) bdtData.get("bdt_qte_temp1"));
            bdt.setBdtQteTemp2((String) bdtData.get("bdt_qte_temp2"));
            bdt.setBdtQuantite((Long) bdtData.get("bdt_quantite"));
            bdt.setBdtReference((String) bdtData.get("bdt_reference"));
            bdt.setBdtRetEstValide((boolean) bdtData.get("bdt_ret_est_valide"));
            bdt.setBdtRetLienFichier((String) bdtData.get("bdt_ret_lien_fichier"));
            bdt.setBdtRetQteBonnes((Long) bdtData.get("bdt_ret_qte_bonne"));
            bdt.setBdtRetQteMauvaise((Long) bdtData.get("bdt_ret_qte_mauvaise"));
            bdt.setBdtRetReference((String) bdtData.get("bdt_ret_reference"));
            bdt.setBdtSpecifsParts((String) bdtData.get("bdt_specifs_parts"));
            bdt.setBdtTempTest((String) bdtData.get("bdt_temp_test"));
            bdt.setBdtValidateurDate((Date) bdtData.get("bdt_validateur_date"));
            bdt.setBdtValidateurUser((String) bdtData.get("bdt_validateur_user"));
            Long artCarteId = (Long) bdtData.get("pk_article_carte");
            Article article0 = articleRepository.findById(artCarteId).orElse(null);
            bdt.setPkArticleCarte(article0);
            Long artEquivId = (Long) bdtData.get("pk_article_equivalent");
            Article article1 = articleRepository.findById(artEquivId).orElse(null);
            bdt.setPkArticleEquivalent(article1);
            Long artInitId = (Long) bdtData.get("pk_article_initial");
            Article article2 = articleRepository.findById(artInitId).orElse(null);
            bdt.setPkArticleInitial(article2);
            Long dshId = (Long) bdtData.get("pk_dossier_homologation");
            DossierHomologation dsh = dossierHomologationRepository.findById(dshId).orElse(null);
            bdt.setPkDossierHomologation(dsh);
            Long fabricantId = (Long) bdtData.get("pk_fabricant");
            Fabricant fabricant = fabricantRepository.findById(fabricantId).orElse(null);
            bdt.setPkFabricant(fabricant);
            bdts.add(bdt);
        }

        ResponseEntity<List<BonTravailTest>> responseEntity = new ResponseEntity<>(bdts, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBdt(@PathVariable("id") long id){
        BonTravailTest deleteBdt = bonTravailTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bdt not exist with id: " + id));;
        bonTravailTestRepository.delete(deleteBdt);
        bonTravailTestRepository.deleteBDTById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BonTravailTest> updateBDT(@PathVariable long id, @RequestBody BonTravailTest bonTravailTest) {
        BonTravailTest existingBdt = bonTravailTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bdt does not exist with id: " + id));

        // Copy only the provided properties while ignoring null or default values
        BeanUtils.copyProperties(bonTravailTest, existingBdt, ArticleService.getNullPropertyNames(bonTravailTest));

        BonTravailTest updatedBdt = bonTravailTestRepository.save(existingBdt);
        return new ResponseEntity<>(updatedBdt, HttpStatus.OK);
    }

    //gestion des boutons
    //envoyer
    @PutMapping("/envoyer/{id}")
    public ResponseEntity<String> doActionEnvoyer(@PathVariable long id, @RequestBody BonTravailTest bonTravailTest) {
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            BonTravailTest updateBDT = bonTravailTestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BDT not exist with id: " + id));
            updateBDT.setBdtNiveauValidation("En attente retour");
            updateBDT.setBdtDemandeurUser(updateBDT.getAppOwner());
            updateBDT.setBdtDateEnvoi(sqlDate);
            updateBDT.setBdtDemandeurDate(sqlDate);
            bonTravailTestRepository.save(updateBDT);
            return new ResponseEntity<>("envoiii " , HttpStatus.OK);
    }
    //valider
    @PutMapping("/valider/{id}")
    public ResponseEntity<BonTravailTest> doActionValider(@PathVariable long id, @RequestBody BonTravailTest bonTravailTest) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        BonTravailTest updateBDT = bonTravailTestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateBDT.setBdtNiveauValidation("Validé");
        updateBDT.setBdtDateRetour(sqlDate);
        updateBDT.setBdtValidateurDate(sqlDate);
        updateBDT.setBdtValidateurUser(updateBDT.getAppOwner());
        updateBDT.setBdtRetEstValide(true);
        updateBDT.setBdtRetReference(bonTravailTest.getBdtRetReference());
        updateBDT.setBdtRetQteBonnes(bonTravailTest.getBdtRetQteBonnes());
        updateBDT.setBdtRetQteMauvaise(bonTravailTest.getBdtRetQteMauvaise());
        updateBDT.setBdtCommentaireValidateur(bonTravailTest.getBdtCommentaireValidateur());
        updateBDT.setBdtCommentaireLabo(bonTravailTest.getBdtCommentaireLabo());
        bonTravailTestRepository.save(updateBDT);
        return new ResponseEntity<>(updateBDT, HttpStatus.OK);
    }
    //refuser
    @PutMapping("/refuser/{id}")
    public ResponseEntity<BonTravailTest> doActionRefuser(@PathVariable long id, @RequestBody BonTravailTest bonTravailTest) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        BonTravailTest updateBDT = bonTravailTestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateBDT.setBdtNiveauValidation("Refusé");
        updateBDT.setBdtDateRetour(sqlDate);
        updateBDT.setBdtValidateurDate(sqlDate);
        updateBDT.setBdtValidateurUser(updateBDT.getAppOwner());
        updateBDT.setBdtRetEstValide(false);
        updateBDT.setBdtRetReference(bonTravailTest.getBdtRetReference());
        updateBDT.setBdtRetQteBonnes(bonTravailTest.getBdtRetQteBonnes());
        updateBDT.setBdtRetQteMauvaise(bonTravailTest.getBdtRetQteMauvaise());
        updateBDT.setBdtCommentaireValidateur(bonTravailTest.getBdtCommentaireValidateur());
        updateBDT.setBdtCommentaireLabo(bonTravailTest.getBdtCommentaireLabo());
        bonTravailTestRepository.save(updateBDT);
        return new ResponseEntity<>(updateBDT, HttpStatus.OK);
    }

    //Reports
    @GetMapping("/report/fichebdt/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportFicheBdt(id);
    }

    @GetMapping("/report/listebdts")
    public ResponseEntity<byte[]> generateReportListeClt() throws IOException, JRException {
        return reportService.exportRapportListeBdt();
    }
}
