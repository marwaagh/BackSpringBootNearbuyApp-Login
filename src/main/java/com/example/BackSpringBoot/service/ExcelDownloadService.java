package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
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
}

