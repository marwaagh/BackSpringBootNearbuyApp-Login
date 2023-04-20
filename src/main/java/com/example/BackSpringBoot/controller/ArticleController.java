package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.FabricantRepository;
import com.example.BackSpringBoot.repository.FamilleComposantRepository;
import com.example.BackSpringBoot.service.ArticleService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/ipersyst/article")
public class ArticleController {
    private  final ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FabricantRepository fabricantRepository;

    @Autowired
    private FamilleComposantRepository familleComposantRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles(){
        List<Article> articles = articleService.findallArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK) ;
    }
        @GetMapping("/serie/{idf}/{idfc}")
        @ResponseBody
        public ResponseEntity<List<Article>>  getArticlesserie(@PathVariable Long idf, @PathVariable Long idfc) {
            String sql = "SELECT * FROM public.article " +
                    "INNER JOIN famillecomposant ON article.id_famillecomposant = famillecomposant.id " +
                    "WHERE article.id_fabricant = ? AND article.id_famillecomposant = ? " +
                    "AND article.art_type_article = ?";
            Object[] params = { idf, idfc, "Composant" };
            List<Map<String, Object>> articlesData = jdbcTemplate.queryForList(sql, params);
            List<Article> articles = new ArrayList<>();

            for (Map<String, Object> articleData : articlesData) {
                Article article = new Article();
                article.setId((Long) articleData.get("id"));
                article.setTs((Timestamp) articleData.get("ts"));
                article.setArtReference((String) articleData.get("art_reference"));
                article.setArtTypeArticle((String) articleData.get("art_type_article"));
                article.setArtTypeComposant((String) articleData.get("art_type_composant"));
                article.setArtCycleVie((Long) articleData.get("art_cycle_vie"));
                article.setArtCouleur((String) articleData.get("art_couleur"));
                article.setArtDateCouleur((Date) articleData.get("art_date_couleur"));
                article.setArtCouleurPrecedente((String) articleData.get("art_couleur_precedente"));
                article.setArtDateCouleurPrecedente((Date) articleData.get("art_date_couleur_precedente"));
                article.setArtSource((String) articleData.get("art_source_changement_couleur"));
                article.setArtLienDocAvisObsolescence((String) articleData.get("art_lien_doc_avis_obsolescence"));
                article.setArtDesignation((String) articleData.get("art_designation"));
                article.setAusrName((String) articleData.get("ausr_name"));
                article.setArtBoitier((String) articleData.get("art_boitier"));
                article.setArtPins((String) articleData.get("art_pins"));
                article.setArtGenerique((String) articleData.get("art_generique"));
                article.setArtCycleVieGenerique((Long) articleData.get("art_cycle_vie_generique"));
                article.setArtEquivalentPotentiels((Long) articleData.get("art_equivalent_potentiels"));
                article.setArtNcage((String) articleData.get("art_ncage"));
                article.setArtNno((String) articleData.get("art_nno"));
                article.setArtRohs((String) articleData.get("art_rohs"));
                article.setArtRohsMsl((String) articleData.get("art_rohs_msl"));
                article.setArtRohsPeakReflow((String) articleData.get("art_rohs_peak_reflow"));
                article.setArtRohsFinishType((String) articleData.get("art_rohs_finish_type"));
                article.setArtDateCreation((Date) articleData.get("art_date_creation"));
                article.setArtLboDate((Date) articleData.get("art_lbo_date"));
                article.setArtTechnologie((String) articleData.get("art_technologie"));
                article.setArtNno((String) articleData.get("art_nno"));
                article.setArtCodeConstructeur((String) articleData.get("art_code_constructeur"));
                article.setArtLeadFramePlating((String) articleData.get("art_lead_frame_plating"));
                article.setArtCarteSn((String) articleData.get("art_carte_sn"));
                article.setArtCarteIndice((String) articleData.get("art_carte_indice"));
                article.setArtCarteVersion((String) articleData.get("art_carte_version"));
                article.setArtInformations((String) articleData.get("art_informations"));
                article.setArtItar((String) articleData.get("art_itar"));
                article.setArtItarPays((String) articleData.get("art_itar_pays"));
                article.setArtItarEccn((String) articleData.get("art_itar_eccn"));
                article.setArtItarUsml((String) articleData.get("art_itar_usml"));
                article.setArtItarMde((String) articleData.get("art_itar_mde"));
                article.setArtItarDateMaj((Date) articleData.get("art_itar_date_maj"));
                article.setArtReachItemWeight((String) articleData.get("art_reach_item_weight"));
                article.setArtReachSvhcPresence((String) articleData.get("art_reach_svhc_presence"));
                article.setArtReachSvhcList((String) articleData.get("art_reach_svhc_list"));
                article.setArtReachSource((String) articleData.get("art_reach_source"));
                article.setArtReachCasAccountedForWeight((String) articleData.get("art_reach_cas_accounted_for_weight"));
                article.setArtReachPdslPresence((String) articleData.get("art_reach_pdsl_presence"));
                article.setArtReachPdslList((String) articleData.get("art_reach_pdsl_list"));
                article.setArtReachDateMaj((Date) articleData.get("art_reach_date_maj"));
                article.setArtReachConflictMineral((String) articleData.get("art_reach_conflict_mineral"));
                article.setArtLienDatasheet((String) articleData.get("art_lien_datasheet"));
                article.setArtLienJustificatif((String) articleData.get("art_lien_justificatif"));
                article.setArtReachLienDisclosure((String) articleData.get("art_reach_lien_disclosure"));
                article.setArtReachLienCOFC((String) articleData.get("art_reach_liencofc"));
                article.setArtReachLienEICCTemplate((String) articleData.get("art_reach_lieneicctemplate"));
                article.setArtNRFND((String) articleData.get("artnrfnd"));
                article.setArtItarManufacturerRemarks((String) articleData.get("art_itar_manufacturer_remarks"));
                article.setArtItarIhsRemarks((String) articleData.get("art_itar_ihs_remarks"));
                article.setArtItarAutre((String) articleData.get("art_itar_autre"));
                article.setArtItarClassificationEU((String) articleData.get("art_itar_classificationeu"));
                article.setArtFrequenceConsultation((String) articleData.get("art_frequence_consultation"));
                article.setArtReferenceMaAERO((String) articleData.get("art_reference_maaero"));
                article.setArtDiametre((String) articleData.get("art_diametre"));
                article.setArtLongueur((String) articleData.get("art_longueur"));
                article.setArtReferenceInitiale((String) articleData.get("art_reference_initiale"));
                article.setArtCertificatDeConformite((String) articleData.get("art_certificat_de_conformite"));
                article.setArtFollowedComponent((String) articleData.get("art_followed_component"));
                Long serieId = (Long) articleData.get("serie_id");
                Article artSerie = articleRepository.findById(serieId).orElse(null);
                article.setArtSerie(artSerie);
                Long fabricantId = (Long) articleData.get("id_fabricant");
                Fabricant fabricant = fabricantRepository.findById(fabricantId).orElse(null);
                article.setFabricant(fabricant);
                Long famillecoposantId = (Long) articleData.get("id_famillecomposant");
                FamilleComposant familleComposant = familleComposantRepository.findById(famillecoposantId).orElse(null);
                article.setFamilleComposant(familleComposant);
                articles.add(article);
            }

            ResponseEntity<List<Article>> responseEntity = new ResponseEntity<>(articles, HttpStatus.OK);
            return responseEntity;
        }

    @GetMapping("/find/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id){
        Article article = articleService.findArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK) ;
    }

    @GetMapping("/findtype/{artTypeArticle}")
    public ResponseEntity<List<Article>> getArticlesByType(@PathVariable("artTypeArticle") String artTypeArticle){
        List<Article> articles = articleService.findArticlesByArtTypeArticle(artTypeArticle);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        Article newArticle = articleService.addArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody Article article){
        Article updateArticle = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not exist with id: " + id));
        updateArticle.setArtReference(article.getArtReference());
        updateArticle.setArtBoitier(article.getArtBoitier());
        updateArticle.setArtCouleurPrecedente(article.getArtCouleurPrecedente());
        updateArticle.setArtCouleur(article.getArtCouleur());
        updateArticle.setArtTypeArticle(article.getArtTypeArticle());
        updateArticle.setArtLboDate(article.getArtLboDate());
        //updateArticle.setFabricant(article.getFabricant());
        //updateArticle.setArtSerie(article.getArtSerie());
        //updateArticle.setFamilleComposant(article.getFamilleComposant());
        //updateArticle.setArtTypeComposant(article.getArtTypeComposant());
        //updateArticle.setArtSerie(article.getArtSerie());

        articleRepository.save(updateArticle) ;
        return new ResponseEntity<>(updateArticle, HttpStatus.OK) ;
        // return "user updated !!!!";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") long id){
        Article deletearticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not exist with id: " + id));;
        articleRepository.delete(deletearticle);
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
