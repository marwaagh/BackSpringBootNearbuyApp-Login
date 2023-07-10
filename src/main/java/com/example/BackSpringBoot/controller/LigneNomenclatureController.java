package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.*;
import com.example.BackSpringBoot.service.FabricantService;
import com.example.BackSpringBoot.service.LigneNomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/ipersyst/lignenomenclature")
public class LigneNomenclatureController {
    private  final LigneNomenclatureService ligneNomenclatureService;
    @Autowired
    private LigneNomenclatureRepository ligneNomenclatureRepository;
    @Autowired
    private NomenclatureRepository nomenclatureRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LigneNomenclatureController(LigneNomenclatureService ligneNomenclatureService) {
        this.ligneNomenclatureService = ligneNomenclatureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LigneNomenclature>> getAllLignenomenclature(){
        List<LigneNomenclature> lignenomenclatures = ligneNomenclatureRepository.findAll();
        return new ResponseEntity<>(lignenomenclatures, HttpStatus.OK) ;
    }

    @GetMapping("/parnomenclature/{idNom}")
    @ResponseBody
    public ResponseEntity<List<LigneNomenclature>> getLignesPerNomenclatures(@PathVariable Long idNom) {
        if (idNom == null) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        String sql1 = "SELECT * FROM public.lignenomenclature WHERE lignenomenclature.nomenclature_id = ?";
        List<Map<String, Object>> lgsData = jdbcTemplate.queryForList(sql1, idNom);
        List<LigneNomenclature> lgs = new ArrayList<>();
        for (Map<String, Object> lgData : lgsData) {
            LigneNomenclature lg = new LigneNomenclature();
            lg.setId((Long) lgData.get("id"));
            lg.setTs((Timestamp) lgData.get("ts"));
            lg.setLnomNumeroLigne((Long) lgData.get("lnom_numero_ligne"));
            lg.setLnomDateCreation((Date) lgData.get("lnom_date_creation"));
            lg.setLnomQuantite((Long) lgData.get("lnom_quantite"));
            lg.setLnomNuLigneClient((String) lgData.get("lnom_nu_ligne_client"));
            lg.setLnomDateRemplacement((Date) lgData.get("lnom_date_remplacement"));
            lg.setLnomNePasBasculer((boolean) lgData.get("lnom_ne_pas_basculer"));
            lg.setLnomDateNePasBasculer((Date) lgData.get("lnom_date_ne_pas_basculer"));
            lg.setLnomEstPerennise((boolean) lgData.get("lnom_est_perennise"));
            lg.setLnomCommentaires((String) lgData.get("lnom_commentaires"));
            lg.setLnomCodeArtMovex((String) lgData.get("lnom_code_art_movex"));
            lg.setLnomCodeArtClient((String) lgData.get("lnom_code_art_client"));
            lg.setLnomArtCritique((String) lgData.get("lnom_art_critique"));
            Long idNomenclature = (Long) lgData.get("nomenclature_id");
            Nomenclature carte = nomenclatureRepository.findById(idNomenclature).orElse(null);
            lg.setNomenclature(carte);
            Long artId = (Long) lgData.get("pk_article_composant_id");
            Article article = articleRepository.findById(artId).orElse(null);
            lg.setPkArticleComposant(article);
            Long dshId = (Long) lgData.get("pk_dossier_validation_id");
            DossierHomologation dossierHomologation = dossierHomologationRepository.findById(dshId).orElse(null);
            lg.setPkDossierValidation(dossierHomologation);
            lgs.add(lg);
        }
        ResponseEntity<List<LigneNomenclature>> responseEntity = new ResponseEntity<>(lgs, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/pardsh/{idDsh}")
    @ResponseBody
    public ResponseEntity<List<LigneNomenclature>> getLigneNomenclaturesParDshs(@PathVariable Long idDsh) {
        String sql1 = "SELECT DISTINCT lg.*, n.nom_reference as nom_carte FROM lignenomenclature lg, nomenclature n, sitefctsys sfs, client_site cs, dossierequivalence ds, dossier_homologation dsh\n" +
                "\t\t\t\tWHERE dsh.id = ? \n" +
                "\t\t\t\tAND cs.id = dsh.pk_client_site_id\n" +
                "\t\t\t\tAND cs.clst_actif = 'true'\n" +
                "\t\t\t\tAND sfs.pk_client_site_id = cs.id\n" +
                "\t\t\t\tAND sfs.stfcsy_decision_perennite = 'p'\n" +
                "\t\t\t\tAND sfs.id = n.site_fct_sys_id\n" +
                "\t\t\t\tAND n.nom_decision_perennite = 'c'\n" +
                "\t\t\t\tAND lg.nomenclature_id = n.id\n" +
                "\t\t\t\tAND lg.lnom_est_perennise = 'true'\n" +
                "\t\t\t\tAND lg.lnom_ne_pas_basculer = 'false'";
        List<Map<String, Object>> lgsData = jdbcTemplate.queryForList(sql1, idDsh);
        List<LigneNomenclature> lgs = new ArrayList<>();
        for (Map<String, Object> lgData : lgsData) {
            LigneNomenclature lg = new LigneNomenclature();
            lg.setId((Long) lgData.get("id"));
            lg.setTs((Timestamp) lgData.get("ts"));
            lg.setLnomNumeroLigne((Long) lgData.get("lnom_numero_ligne"));
            lg.setLnomDateCreation((Date) lgData.get("lnom_date_creation"));
            lg.setLnomQuantite((Long) lgData.get("lnom_quantite"));
            lg.setLnomNuLigneClient((String) lgData.get("lnom_nu_ligne_client"));
            lg.setLnomDateRemplacement((Date) lgData.get("lnom_date_remplacement"));
            lg.setLnomNePasBasculer((boolean) lgData.get("lnom_ne_pas_basculer"));
            lg.setLnomDateNePasBasculer((Date) lgData.get("lnom_date_ne_pas_basculer"));
            lg.setLnomEstPerennise((boolean) lgData.get("lnom_est_perennise"));
            lg.setLnomCommentaires((String) lgData.get("lnom_commentaires"));
            lg.setLnomCodeArtMovex((String) lgData.get("lnom_code_art_movex"));
            lg.setLnomCodeArtClient((String) lgData.get("lnom_code_art_client"));
            lg.setLnomArtCritique((String) lgData.get("lnom_art_critique"));
            Long idNom = (Long) lgData.get("nomenclature_id");
            Nomenclature carte = nomenclatureRepository.findById(idNom).orElse(null);
            lg.setNomenclature(carte);

            Long dshId = (Long) lgData.get("pk_dossier_validation_id");
            DossierHomologation dossierHomologation = dossierHomologationRepository.findById(dshId).orElse(null);
            lg.setPkDossierValidation(dossierHomologation);
            lgs.add(lg);
        }
        ResponseEntity<List<LigneNomenclature>> responseEntity = new ResponseEntity<>(lgs, HttpStatus.OK);
        return responseEntity;
    }
// liste des lignes nomenclatures déja basculées
    @GetMapping("/dejabasc/{idDsh}")
    @ResponseBody
    public ResponseEntity<List<LigneNomenclature>> getLigneNomenclaturesDejaBasc(@PathVariable Long idDsh) {
        String sql1 = "SELECT DISTINCT lg.*, n.nom_reference as nom_carte FROM lignenomenclature lg, nomenclature n, sitefctsys sfs, client_site cs, dossierequivalence ds, dossier_homologation dsh\n" +
                "\t\t\t\tWHERE dsh.id = ? \n" +
                "\t\t\t\tAND cs.id = dsh.pk_client_site_id\n" +
                "\t\t\t\tAND cs.clst_actif = 'true'\n" +
                "\t\t\t\tAND sfs.pk_client_site_id = cs.id\n" +
                "\t\t\t\tAND sfs.stfcsy_decision_perennite = 'p'\n" +
                "\t\t\t\tAND sfs.id = n.site_fct_sys_id\n" +
                "\t\t\t\tAND n.nom_decision_perennite = 'c'\n" +
                "\t\t\t\tAND lg.nomenclature_id = n.id\n" +
                "\t\t\t\tAND lg.lnom_est_perennise = 'false'\n" +
                "\t\t\t\tAND lg.lnom_ne_pas_basculer = 'false'";
        List<Map<String, Object>> lgsData = jdbcTemplate.queryForList(sql1, idDsh);
        List<LigneNomenclature> lgs = new ArrayList<>();
        for (Map<String, Object> lgData : lgsData) {
            LigneNomenclature lg = new LigneNomenclature();
            lg.setId((Long) lgData.get("id"));
            lg.setTs((Timestamp) lgData.get("ts"));
            lg.setLnomNumeroLigne((Long) lgData.get("lnom_numero_ligne"));
            lg.setLnomDateCreation((Date) lgData.get("lnom_date_creation"));
            lg.setLnomQuantite((Long) lgData.get("lnom_quantite"));
            lg.setLnomNuLigneClient((String) lgData.get("lnom_nu_ligne_client"));
            lg.setLnomDateRemplacement((Date) lgData.get("lnom_date_remplacement"));
            lg.setLnomNePasBasculer((boolean) lgData.get("lnom_ne_pas_basculer"));
            lg.setLnomDateNePasBasculer((Date) lgData.get("lnom_date_ne_pas_basculer"));
            lg.setLnomEstPerennise((boolean) lgData.get("lnom_est_perennise"));
            lg.setLnomCommentaires((String) lgData.get("lnom_commentaires"));
            lg.setLnomCodeArtMovex((String) lgData.get("lnom_code_art_movex"));
            lg.setLnomCodeArtClient((String) lgData.get("lnom_code_art_client"));
            lg.setLnomArtCritique((String) lgData.get("lnom_art_critique"));
            Long idNom = (Long) lgData.get("nomenclature_id");
            Nomenclature carte = nomenclatureRepository.findById(idNom).orElse(null);
            lg.setNomenclature(carte);

            Long dshId = (Long) lgData.get("pk_dossier_validation_id");
            DossierHomologation dossierHomologation = dossierHomologationRepository.findById(dshId).orElse(null);
            lg.setPkDossierValidation(dossierHomologation);
            lgs.add(lg);
        }
        ResponseEntity<List<LigneNomenclature>> responseEntity = new ResponseEntity<>(lgs, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/checked/{idDsh}")
    @ResponseBody
    public ResponseEntity<List<LigneNomenclature>> getLigneNomenclaturesCheckced(@PathVariable Long idDsh) {
        String sql1 = "SELECT DISTINCT lg.*, n.nom_reference as nom_carte FROM lignenomenclature lg, nomenclature n, sitefctsys sfs, client_site cs, dossierequivalence ds, dossier_homologation dsh\n" +
                "\t\t\t\tWHERE dsh.id = ? \n" +
                "\t\t\t\tAND cs.id = dsh.pk_client_site_id\n" +
                "\t\t\t\tAND cs.clst_actif = 'true'\n" +
                "\t\t\t\tAND sfs.pk_client_site_id = cs.id\n" +
                "\t\t\t\tAND sfs.stfcsy_decision_perennite = 'p'\n" +
                "\t\t\t\tAND sfs.id = n.site_fct_sys_id\n" +
                "\t\t\t\tAND n.nom_decision_perennite = 'c'\n" +
                "\t\t\t\tAND lg.nomenclature_id = n.id\n" +
                "\t\t\t\tAND lg.lnom_est_perennise = 'true'\n" +
                "\t\t\t\tAND lg.lnom_ne_pas_basculer = 'true'";
        List<Map<String, Object>> lgsData = jdbcTemplate.queryForList(sql1, idDsh);
        List<LigneNomenclature> lgs = new ArrayList<>();
        for (Map<String, Object> lgData : lgsData) {
            LigneNomenclature lg = new LigneNomenclature();
            lg.setId((Long) lgData.get("id"));
            lg.setTs((Timestamp) lgData.get("ts"));
            lg.setLnomNumeroLigne((Long) lgData.get("lnom_numero_ligne"));
            lg.setLnomDateCreation((Date) lgData.get("lnom_date_creation"));
            lg.setLnomQuantite((Long) lgData.get("lnom_quantite"));
            lg.setLnomNuLigneClient((String) lgData.get("lnom_nu_ligne_client"));
            lg.setLnomDateRemplacement((Date) lgData.get("lnom_date_remplacement"));
            lg.setLnomNePasBasculer((boolean) lgData.get("lnom_ne_pas_basculer"));
            lg.setLnomDateNePasBasculer((Date) lgData.get("lnom_date_ne_pas_basculer"));
            lg.setLnomEstPerennise((boolean) lgData.get("lnom_est_perennise"));
            lg.setLnomCommentaires((String) lgData.get("lnom_commentaires"));
            lg.setLnomCodeArtMovex((String) lgData.get("lnom_code_art_movex"));
            lg.setLnomCodeArtClient((String) lgData.get("lnom_code_art_client"));
            lg.setLnomArtCritique((String) lgData.get("lnom_art_critique"));
            Long idNom = (Long) lgData.get("nomenclature_id");
            Nomenclature carte = nomenclatureRepository.findById(idNom).orElse(null);
            lg.setNomenclature(carte);

            Long dshId = (Long) lgData.get("pk_dossier_validation_id");
            DossierHomologation dossierHomologation = dossierHomologationRepository.findById(dshId).orElse(null);
            lg.setPkDossierValidation(dossierHomologation);
            lgs.add(lg);
        }
        ResponseEntity<List<LigneNomenclature>> responseEntity = new ResponseEntity<>(lgs, HttpStatus.OK);
        return responseEntity;
    }


    @PutMapping("/nepasbasculer/{nepasbasc}/{numlg}")
    @ResponseBody
    public ResponseEntity<String> setNePasBasculer(@PathVariable boolean nepasbasc, @PathVariable Long numlg) {
        String sql3 = "UPDATE public.lignenomenclature SET lnom_ne_pas_basculer = ? WHERE lnom_numero_ligne = ?; ";
        int rowsAffected = jdbcTemplate.update(sql3, nepasbasc, numlg);

        if (rowsAffected > 0) {
            return ResponseEntity.ok("LigneNomenclature updated successfully.");
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if no rows were affected
        }
    }

    @PostMapping("/add")
    public ResponseEntity<LigneNomenclature> addLigneNomenclatures(@RequestBody LigneNomenclature ligneNomenclature){
        LigneNomenclature newlignenomenclature = ligneNomenclatureService.addLigneNomenclature(ligneNomenclature);
        return new ResponseEntity<>(newlignenomenclature, HttpStatus.CREATED) ;
    }
}
