package com.example.BackSpringBoot.Files;

import com.example.BackSpringBoot.model.DossierEquivalence;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    public static ByteArrayInputStream deqsToExcel(List<DossierEquivalence> deqs) throws IOException {
        String[] COLUMNs = {"ID", "ts", "reference", "niveau validation", "date niveau validation", "fichier init", "fichier final", "fichier comparatif", "deandeur", "date demande", "validateur", "date validation", "date creation", "couleur art init", "date couleur art init", "lbo art init", "couleur art equiv", "date couleur art equiv", "lbo art equiv","commentaires demandeur", "commentaires validateur", "app owner", "suivi"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("deqs");

            // Header
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.GREY_25_PERCENT.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // Data rows
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

            int rowIdx = 1;
            for (DossierEquivalence deq : deqs) {
                Row row = sheet.createRow(rowIdx++);
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
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
