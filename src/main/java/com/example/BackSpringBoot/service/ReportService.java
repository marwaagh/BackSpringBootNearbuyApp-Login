package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.appuser.AppUser;
import com.example.BackSpringBoot.appuser.AppUserRepository;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private DossierEquivalenceRepository dossierEquivalenceRepository;

    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ClientSiteRepository clientSiteRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;

    @Autowired
    private BonTravailTestRepository bonTravailTestRepository;

    @Autowired
    private CodeArticleRepository codeArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public ResponseEntity<byte[]> exportRapport() throws IOException, JRException {
        List<DossierEquivalence> deqq= dossierEquivalenceRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Deqq.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\deqq.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\deqq.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"deqq.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> exportRapportDeq(Long id) throws IOException, JRException {
        DossierEquivalence deq = dossierEquivalenceRepository.findById(id).orElse(null);
        if (deq == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Deq.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<DossierEquivalence> deqList = Collections.singletonList(deq);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\deq.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\deq.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"deq.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    public ResponseEntity<byte[]> exportRapportArboAscInit(Long id) throws IOException, JRException {
        DossierEquivalence deq = dossierEquivalenceRepository.findById(id).orElse(null);
        if (deq == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        Article articleInit = articleRepository.findById(deq.getPkArticleInitial().getId()).orElse(null);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ArboAsc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<Article> artList = Collections.singletonList(articleInit);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(artList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ArboAsc.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> exportRapportArboAscEq(Long id) throws IOException, JRException {
        DossierEquivalence deq = dossierEquivalenceRepository.findById(id).orElse(null);
        if (deq == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        Article articleEq = articleRepository.findById(deq.getPkArticleEquivalent().getId()).orElse(null);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ArboAsc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<Article> artList = Collections.singletonList(articleEq);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(artList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ArboAsc.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //dynamic
    /*public ResponseEntity<byte[]> exportRapportDynamic(Long id) throws IOException, JRException {
        DossierEquivalence deq = appUser.findAll().orElse(null);
        if (deq == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        Article articleEq = articleRepository.findById(deq.getPkArticleEquivalent().getId()).orElse(null);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ArboAsc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<Article> artList = Collections.singletonList(articleEq);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(artList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ArboAsc.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ArboAsc.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }*/

    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheSite(Long id) throws IOException, JRException {
        Site site = siteRepository.findById(id).orElse(null);
        if (site == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheSite.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<Site> sitList = Collections.singletonList(site);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(sitList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheSite.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheSite.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheSite.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //liste sites
    public ResponseEntity<byte[]> exportRapportListeSite() throws IOException, JRException {
        List<Site> deqq= siteRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeSites.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeSites.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeSites.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeSites.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }



    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheClient(Long id) throws IOException, JRException {
        Client clt = clientRepository.findById(id).orElse(null);
        if (clt == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheClient.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<Client> cltList = Collections.singletonList(clt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(cltList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheClient.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheClient.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheClient.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //liste client

    public ResponseEntity<byte[]> exportRapportListeClt() throws IOException, JRException {
        List<Client> deqq= clientRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeClients.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeClients.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeClients.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeClients.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheClientSite(Long id) throws IOException, JRException {
        ClientSite clt = clientSiteRepository.findById(id).orElse(null);
        if (clt == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheClientSite.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<ClientSite> cltList = Collections.singletonList(clt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(cltList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheClientSite.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheClientSite.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheClientSite.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //liste clientsite

    public ResponseEntity<byte[]> exportRapportListeCltSite() throws IOException, JRException {
        List<ClientSite> deqq= clientSiteRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeClientSites.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeClientSites.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeClientSites.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeClientSites.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheUser(Long id) throws IOException, JRException {
        AppUser clt = appUserRepository.findById(id).orElse(null);
        if (clt == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheUser.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<AppUser> cltList = Collections.singletonList(clt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(cltList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheUser.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheUser.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheUser.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //liste client

    public ResponseEntity<byte[]> exportRapportListeUser() throws IOException, JRException {
        List<AppUser> deqq= appUserRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeUsers.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeUsers.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeUsers.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeUsers.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheDsh(Long id) throws IOException, JRException {
        DossierHomologation clt = dossierHomologationRepository.findById(id).orElse(null);
        if (clt == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheDsh.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<DossierHomologation> cltList = Collections.singletonList(clt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(cltList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheDsh.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheDsh.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheDsh.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //liste client

    public ResponseEntity<byte[]> exportRapportListeDsh() throws IOException, JRException {
        List<DossierHomologation> deqq= dossierHomologationRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeDshs.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeDshs.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeDshs.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeDshs.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    //liste client

    public ResponseEntity<byte[]> exportRapportListeBdt() throws IOException, JRException {
        List<BonTravailTest> deqq= bonTravailTestRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListeBdts.jrxml") ;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(deqq);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "java techie") ;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\ListeBdts.pdf");
        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\ListeBdts.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ListeBdts.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    //fiche client
    public ResponseEntity<byte[]> exportRapportFicheBdt(Long id) throws IOException, JRException {
        BonTravailTest clt = bonTravailTestRepository.findById(id).orElse(null);
        if (clt == null) {
            // Handle the case where the DossierEquivalence with the given ID is not found
            return ResponseEntity.notFound().build();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:FicheBdt.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Pass the DossierEquivalence as a single-item list to the JRBeanCollectionDataSource
        List<BonTravailTest> cltList = Collections.singletonList(clt);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(cltList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "java techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Admin\\Documents\\Reports\\FicheBdt.pdf");

        // Read the PDF file and convert it to a byte array
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Admin\\Documents\\Reports\\FicheBdt.pdf");
        byte[] bytes = IOUtils.toByteArray(inputStream);

        // Set the content type and attachment header, and return the PDF file's content as a ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FicheBdt.pdf\"");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
