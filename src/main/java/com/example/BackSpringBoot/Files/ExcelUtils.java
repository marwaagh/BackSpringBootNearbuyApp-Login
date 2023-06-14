package com.example.BackSpringBoot.Files;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
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
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
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


    public static ByteArrayInputStream dshsToExcel(List<DossierHomologation> dshs) throws IOException {
        String[] COLUMNs = {"Client Site", "Référence", "Niveau de Validation", "Date Niveau Validation", "Date Création", "Init Suivi", "Type", "Référence Article Init.", "Fabricant", "NCage_Fab Initial", "Statut Article Init.", "LBO Init", "NCage_Article Init.", "NNO Init", "Référence Article Equiv.", "Fabricant", "NCage_Fab Equiv", "Statut Article Equiv.", "LBO Equiv", "Ncage_Article Equiv.", "NNO Equiv", "Equiv Suivi", "Nom demandeur", "Dossier Equivalence", "Niveau validation DEQ", "Date Niveau Validation DEQ", "Comm. Demandeur DEQ", "Comm. Validateur DEQ", "Date Envoi", "Commentaire", "Qté Rex", "Rex", "Att. env.", "Init. Cmde.", "Equiv. Cmde", "Lien Fichier Réponse", "Mot clé", "Quantité disponible"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("dossierhomologation1");

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
            for (DossierHomologation dsh : dshs) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(dsh.getPkClientSite().getClstReference());
                row.createCell(1).setCellValue(dsh.getDshReference());
                row.createCell(2).setCellValue(dsh.getDshNiveauValidation());
                row.createCell(3).setCellValue(dsh.getDshDateNiveauValidation());
                row.createCell(4).setCellValue(dsh.getDshDateCreation());
                if (dsh.getPkEquivalence().getPkArticleInitial() != null) {
                    row.createCell(5).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtFollowedComponent());
                    row.createCell(6).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtTypeArticle());
                    row.createCell(7).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtReference());
                    row.createCell(8).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcReference());
                    row.createCell(9).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcNCAGE());
                    row.createCell(10).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtCouleur());
                    row.createCell(11).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtLboDate());
                    row.createCell(12).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtNcage());
                    row.createCell(13).setCellValue(dsh.getPkEquivalence().getPkArticleInitial().getArtNno());
                } else {
                    row.createCell(5).setCellValue("");
                    row.createCell(6).setCellValue("");
                    row.createCell(7).setCellValue("");
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue("");
                    row.createCell(10).setCellValue("");
                    row.createCell(11).setCellValue("");
                    row.createCell(12).setCellValue("");
                    row.createCell(13).setCellValue("");
                }
                if(dsh.getPkEquivalence().getPkArticleEquivalent() != null){
                    row.createCell(14).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtReference());
                    row.createCell(15).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getFabricant().fbcReference);
                    row.createCell(16).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getFabricant().getFbcNCAGE());
                    row.createCell(17).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtCouleur());
                    row.createCell(18).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtLboDate());
                    row.createCell(19).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtNcage());
                    row.createCell(20).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtNno());
                    row.createCell(21).setCellValue(dsh.getPkEquivalence().getPkArticleEquivalent().getArtFollowedComponent());
                } else {
                    row.createCell(14).setCellValue("");
                    row.createCell(15).setCellValue("");
                    row.createCell(16).setCellValue("");
                    row.createCell(17).setCellValue("");
                    row.createCell(18).setCellValue("");
                    row.createCell(19).setCellValue("");
                    row.createCell(20).setCellValue("");
                    row.createCell(21).setCellValue("");
                }

                row.createCell(22).setCellValue(dsh.getPkEquivalence().getDsequivDemandeurUser());
                row.createCell(23).setCellValue(dsh.getPkEquivalence().getDsequivNiveauValidation());
                row.createCell(24).setCellValue(dsh.getPkEquivalence().getDsequivDateNiveauValidation());
                row.createCell(25).setCellValue(dsh.getPkEquivalence().getDsequivCommentairesDemandeur());
                row.createCell(26).setCellValue(dsh.getPkEquivalence().getDsequivCommentairesValidateur());
                row.createCell(27).setCellValue(dsh.getDshDateEnvoiDossier());
                row.createCell(28).setCellValue(dsh.getDshRemarquesClient());
                row.createCell(29).setCellValue(dsh.getDshQteRex());
                row.createCell(30).setCellValue(dsh.getDshDemandeRex());
                row.createCell(31).setCellValue(dsh.getDshEnAttenteEnvoi());
                row.createCell(32).setCellValue(dsh.getDshArtInitInCmde());
                row.createCell(33).setCellValue(dsh.getDshArtEquivInCmde());
                row.createCell(34).setCellValue(dsh.getDshLienFichierReponseClient());
               //mot clé row.createCell(25).setCellValue();
                row.createCell(36).setCellValue(dsh.getDshQteDisponible());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
