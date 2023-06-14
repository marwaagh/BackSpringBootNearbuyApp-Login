package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelDownloadService {

    @Autowired
    private DossierEquivalenceRepository dossierEquivalenceRepository;
    @Autowired
    private DossierHomologationRepository dossierHomologationRepository;

    //List of DEQs to Excel file
    public byte[] downloadExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("deqs");

        List<DossierEquivalence> deqs = dossierEquivalenceRepository.findAll();
        int rowIndex = 0;
        for (DossierEquivalence deq : deqs) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(deq.getId());
            row.createCell(1).setCellValue(deq.getTs());
            row.createCell(2).setCellValue(deq.getDsequivReference());
            row.createCell(3).setCellValue(deq.getDsequivNiveauValidation());
            row.createCell(4).setCellValue(deq.getDsequivDateNiveauValidation());
            row.createCell(5).setCellValue(deq.getDsequivLienFichierInitial());
            row.createCell(6).setCellValue(deq.getDsequivLienFichierFinal());
            row.createCell(7).setCellValue(deq.getDsequivLienFichierComparatif());
            row.createCell(8).setCellValue(deq.getDsequivDemandeurUser());
            row.createCell(9).setCellValue(deq.getDsequivDateDemandeur());
            row.createCell(10).setCellValue(deq.getDsequivValidateurUser());
            row.createCell(11).setCellValue(deq.getDsequivDateValidateur());
            row.createCell(12).setCellValue(deq.getDsequivDateCreation());
            row.createCell(13).setCellValue(deq.getDsequivCouleurArtInit());
            row.createCell(14).setCellValue(deq.getDsequivDateCouleurArtInit());
            row.createCell(15).setCellValue(deq.getDsequivDateLboArtInit());
            row.createCell(16).setCellValue(deq.getDsequivCouleurArtEq());
            row.createCell(17).setCellValue(deq.getDsequivDateCouleurArtEq());
            row.createCell(18).setCellValue(deq.getDsequivDateLboArtEq());
            row.createCell(19).setCellValue(deq.getDsequivCommentairesDemandeur());
            row.createCell(20).setCellValue(deq.getDsequivCommentairesValidateur());
            row.createCell(21).setCellValue(deq.getAppOwner());
            row.createCell(22).setCellValue(deq.getDsequivFollowedComponent());
            row.createCell(23).setCellValue(deq.getDsequivFollowedComponentEquiv());
            row.createCell(24).setCellValue(deq.getDsequivEnAttenteValidation());
            row.createCell(25).setCellValue(deq.getDsequivClients());
            // add more cells as needed
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        // write byte array to file
        byte[] bytes = baos.toByteArray();
        FileOutputStream stream = new FileOutputStream("output.xlsx");
        stream.write(bytes);
        stream.close();

        return bytes;
    }

    //List of DSHs to Excel file
    public byte[] downloadExcelDSH() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("dossierhomologation1");

        List<DossierHomologation> dshs = dossierHomologationRepository.findAll();
        int rowIndex = 0;
        for (DossierHomologation dsh : dshs) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(dsh.getPkClientSite().getClstReference());
            row.createCell(1).setCellValue(dsh.getDshReference());
            row.createCell(2).setCellValue(dsh.getDshNiveauValidation());
            row.createCell(3).setCellValue(dsh.getDshDateNiveauValidation());
            row.createCell(4).setCellValue(dsh.getDshDateCreation());
            row.createCell(5).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtFollowedComponent());
            row.createCell(6).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtTypeArticle());
            row.createCell(7).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtReference());
            row.createCell(8).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcReference());
            row.createCell(9).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcNCAGE());
            row.createCell(10).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtCouleur());
            row.createCell(11).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtLboDate());
            row.createCell(12).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtNcage());
            row.createCell(13).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtNno());
            row.createCell(14).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtReference());
            row.createCell(15).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getFabricant().getFbcReference());
            row.createCell(16).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getFabricant().getFbcNCAGE());
            row.createCell(17).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtCouleur());
            row.createCell(18).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtLboDate());
            row.createCell(19).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtNcage());
            row.createCell(20).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtNno());
            row.createCell(21).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtFollowedComponent());
            row.createCell(22).setCellValue(dsh.getPkEquivalence().getDsequivDemandeurUser());
            row.createCell(23).setCellValue(dsh.getPkEquivalence().getDsequivReference());
            row.createCell(24).setCellValue(dsh.getPkEquivalence().getDsequivNiveauValidation());
            row.createCell(25).setCellValue(dsh.getPkEquivalence().getDsequivDateNiveauValidation());
            row.createCell(26).setCellValue(dsh.getPkEquivalence().getDsequivCommentairesDemandeur());
            row.createCell(27).setCellValue(dsh.getPkEquivalence().getDsequivCommentairesValidateur());
            row.createCell(28).setCellValue(dsh.getDshDateEnvoiDossier());
            row.createCell(29).setCellValue(dsh.getDshRemarquesClient());
            row.createCell(30).setCellValue(dsh.getDshQteRex());
            row.createCell(31).setCellValue(dsh.getDshDemandeRex());
            row.createCell(32).setCellValue(dsh.getDshEnAttenteEnvoi());
            row.createCell(33).setCellValue(dsh.getDshArtInitInCmde());
            row.createCell(34).setCellValue(dsh.getDshArtEquivInCmde());
            row.createCell(35).setCellValue(dsh.getDshLienFichierReponseClient());
            //row.createCell(36).setCellValue(); //mot clé
            row.createCell(37).setCellValue(dsh.getDshQteDisponible());

        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        // write byte array to file
        byte[] bytes = baos.toByteArray();
        FileOutputStream stream = new FileOutputStream("output.xlsx");
        stream.write(bytes);
        stream.close();

        return bytes;
    }
}

