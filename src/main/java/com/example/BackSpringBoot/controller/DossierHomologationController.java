package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.Dao.BasculementDao;
import com.example.BackSpringBoot.Dao.StockageDao;
import com.example.BackSpringBoot.Files.ExcelUtils;
import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import com.example.BackSpringBoot.repository.LigneNomenclatureRepository;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.DossierHomologationService;
import com.example.BackSpringBoot.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/dossierhomologation")
public class DossierHomologationController {
    private  final DossierHomologationService dossierHomologationService;

    @Autowired
    private ReportService reportService;
    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;
    @Autowired
    private LigneNomenclatureRepository ligneNomenclatureRepository;
    private final StockageDao stockageDao;
    private final BasculementDao basculementDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DossierHomologationController(DossierHomologationService dossierHomologationService, DossierHomologationRepository dossierHomologationRepository, StockageDao stockageDao, BasculementDao basculementDao) {
        this.dossierHomologationService = dossierHomologationService;
        this.dossierHomologationRepository = dossierHomologationRepository;
        this.stockageDao = stockageDao;
        this.basculementDao = basculementDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DossierHomologation>> getAllDSHs(){
        List<DossierHomologation> dshs = dossierHomologationService.findallDossierHomologations();
        return new ResponseEntity<>(dshs, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DossierHomologation> getDsh(@PathVariable long id){
        DossierHomologation dossierHomologation = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("dsh not exist with id: " + id));
        return new ResponseEntity<>(dossierHomologation, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<DossierHomologation> addDSH(@RequestBody DossierHomologation dossierHomologation){
        DossierHomologation newDSH = dossierHomologationService.addDossierHomologation(dossierHomologation);
        return new ResponseEntity<>(newDSH, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DossierHomologation> updateDSH(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation){
        DossierHomologation existingDsh = dossierHomologationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("dSH does not exist with id: " + id));
        BeanUtils.copyProperties(dossierHomologation, existingDsh, ArticleService.getNullPropertyNames(dossierHomologation));
        DossierHomologation updatedDsh = dossierHomologationRepository.save(existingDsh);
        return new ResponseEntity<>(updatedDsh, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDSH(@PathVariable("id") long id){
        DossierHomologation deleteDSH = dossierHomologationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));;
        dossierHomologationRepository.delete(deleteDSH);
        dossierHomologationService.deleteDossierHomologation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //gestion des boutons

    //envoyer
    @PutMapping("/envoyer/{id}")
    public ResponseEntity<String> doActionEnvoyer(@PathVariable long id) {
        try {
            java.util.Date currentDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
            updateDSH.setDshNiveauValidation("En attente validation");
            updateDSH.setDshDateNiveauValidation(sqlDate);
            updateDSH.setDshDateEnvoiDossier(sqlDate);
            //updateDSH.setDshLienFichierDemande(dossierHomologation.getDshLienFichierDemande());
            dossierHomologationRepository.save(updateDSH);
            dossierHomologationService.EnvoiMail(dossierHomologationService.createDossierHomologationRequest(updateDSH));
            return new ResponseEntity<>("Mail envoyé", HttpStatus.OK);
          } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>("Une erreur s'est produite: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //valider + reserve
    @PutMapping("/valideravec/{id}")
    public ResponseEntity<DossierHomologation> doActionValiderAvec(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateDSH.setDshNiveauValidation("Validé avec réserve");
        updateDSH.setDshDateNiveauValidation(sqlDate);
        updateDSH.setDshRemarquesClient(dossierHomologation.getDshRemarquesClient());
        dossierHomologationRepository.save(updateDSH);
        return new ResponseEntity<>(updateDSH, HttpStatus.OK);
    }
    //valider ss reserve
    @PutMapping("/validersans/{id}")
    public ResponseEntity<DossierHomologation> doActionValiderSans(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateDSH.setDshNiveauValidation("Validé sans réserve");
        updateDSH.setDshDateNiveauValidation(sqlDate);
        updateDSH.setDshRemarquesClient(dossierHomologation.getDshRemarquesClient());
        dossierHomologationRepository.save(updateDSH);
        return new ResponseEntity<>(updateDSH, HttpStatus.OK);
        }
    //refuser
    @PutMapping("/refuser/{id}")
    public ResponseEntity<DossierHomologation> doActionRefuser(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateDSH.setDshNiveauValidation("Refusé");
        updateDSH.setDshDateNiveauValidation(sqlDate);
        updateDSH.setDshRemarquesClient(dossierHomologation.getDshRemarquesClient());
        dossierHomologationRepository.save(updateDSH);
        return new ResponseEntity<>(updateDSH, HttpStatus.OK);
    }
    //sans traitement
    @PutMapping("/sanstraitement/{id}")
    public ResponseEntity<DossierHomologation> doActionSansTraitement(@PathVariable long id, @RequestBody DossierHomologation dossierHomologation) {
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        DossierHomologation updateDSH = dossierHomologationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DEQ not exist with id: " + id));
        updateDSH.setDshNiveauValidation("Sans traitement");
        updateDSH.setDshDateNiveauValidation(sqlDate);
        updateDSH.setDshRemarquesClient(dossierHomologation.getDshRemarquesClient());
        dossierHomologationRepository.save(updateDSH);
        return new ResponseEntity<>(updateDSH, HttpStatus.OK);
    }

    //list of dshs to Excel file
    @GetMapping("/exporttoexcel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        String fileName = "dshs.xlsx";
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);

        List<DossierHomologation> dshs = dossierHomologationRepository.findAll();
        ByteArrayInputStream inputStream = ExcelUtils.dshsToExcel(dshs);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    /*select commande client per clientsite
    @GetMapping("/equiv/{idinit}")
    @ResponseBody
    public ResponseEntity<List<DossierEquivalence>> getArticlesserie(@PathVariable Long idinit) {
        String sql = "SELECT id " +
                "FROM public.commande_client, public. " +
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
    */

    // stockage
    @PostMapping("/stockage/{id_dsh}")
    public ResponseEntity<String> callStockageFunction(@PathVariable long id_dsh, @RequestBody StockageRequest stockageRequest) {
        String sql0 = "SELECT pk_client_site_id FROM dossier_homologation WHERE id = ?";
        Long pkClientSiteIdFromDsh = jdbcTemplate.queryForObject(sql0, Long.class, id_dsh);
        String sql1 = "SELECT id FROM public.commande_client WHERE pk_client_site_id = ? LIMIT 1";
        Integer dshQteDisponible = stockageRequest.getDshQteDisponible();
        LocalDate currentDate = LocalDate.now();
        Date dshDateStockage = java.sql.Date.valueOf(currentDate);
        String sql2 = "SELECT id FROM public.code_article WHERE pk_client_site_id = ? LIMIT 1";
        long pkCodeArticle =  jdbcTemplate.queryForObject(sql2, Long.class, pkClientSiteIdFromDsh);
        //String appOwner = stockageRequest.getAppOwner();
        Long pkCommandeClient = jdbcTemplate.queryForObject(sql1, Long.class, pkClientSiteIdFromDsh);
        String sql3 = "SELECT cmd_reference FROM public.commande_client WHERE pk_client_site_id = ? LIMIT 1";
        String refCmdeClt = jdbcTemplate.queryForObject(sql3, String.class, pkClientSiteIdFromDsh);
        String sql4 = "SELECT clst_reference FROM public.client_site WHERE id = ? LIMIT 1";
        String NomClst = jdbcTemplate.queryForObject(sql4, String.class, pkClientSiteIdFromDsh);
        if (refCmdeClt.equals("STOCK")) {
            return ResponseEntity.status(HttpStatus.OK).body(stockageDao.callStockageFunction(id_dsh, dshQteDisponible, pkCommandeClient, pkCodeArticle, "marwa"));
        } else {
            String errorMessage = "il n'existe pas une commande de reference STOCK pour le client site: " + NomClst + "\n Veuillez créer la commande avant.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PostMapping("/basculement/{id_dsh}")
    public String callBasculementFunction(@PathVariable long id_dsh) {
        return basculementDao.callBasculementFunction(id_dsh, "marwa");
    }

    //Reports
    @GetMapping("/report/fichedsh/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportFicheDsh(id);
    }

    @GetMapping("/report/listedshs")
    public ResponseEntity<byte[]> generateReportListeClt() throws IOException, JRException {
        return reportService.exportRapportListeDsh();
    }

    @PostMapping("/addFromExcel")
    public ResponseEntity<List<DossierHomologation>> uploadDshData(@RequestParam("file") MultipartFile file) throws IOException {
        List<DossierHomologation> dshs = dossierHomologationService.addDossierHomologationsFromExcelFile(file);
        return new ResponseEntity<>(dshs, HttpStatus.CREATED);
    }
}
