package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
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

}
