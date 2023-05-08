package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ExcelUploadService {

    public  static boolean isValidExcelFile(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true ;
        } else {
            return false ;
        }
    }

    //convert excel to list of deqs
    public static List<DossierEquivalence> getDEQDataFromExcel(InputStream inputStream, DossierEquivalenceRepository dossierEquivalenceRepository){
        List<DossierEquivalence> deqs = new ArrayList<>();
        List<DossierEquivalence> existingDeqs = dossierEquivalenceRepository.findAll();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("deqs");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                DossierEquivalence deq = new DossierEquivalence();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                deq.setId((long) cell.getNumericCellValue());
                            } else {
                                deq.setId(Long.parseLong(cell.getStringCellValue()));
                            }
                            break;
                        }
                        case 2 -> {
                            String reference = cell.getStringCellValue();
                            DossierEquivalence existingDeq = dossierEquivalenceRepository.findByDsequivReference(reference);
                            if(existingDeq != null){
                                dossierEquivalenceRepository.delete(existingDeq);
                            }
                            deq.setDsequivReference(reference);
                            break;
                        }
                        case 3 -> deq.setDsequivNiveauValidation(cell.getStringCellValue());
                        case 4 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date date = (Date) cell.getDateCellValue();
                                deq.setDsequivDateNiveauValidation(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateNiveauValidation(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateNiveauValidation(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 5 -> deq.setDsequivLienFichierInitial(cell.getStringCellValue());
                        case 6 -> deq.setDsequivLienFichierFinal(cell.getStringCellValue());
                        case 7 -> deq.setDsequivLienFichierComparatif(cell.getStringCellValue());
                        case 8 -> deq.setDsequivDemandeurUser(cell.getStringCellValue());
                        case 9 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date date = (Date) cell.getDateCellValue();
                                deq.setDsequivDateDemandeur(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateDemandeur(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateDemandeur(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 10 -> deq.setDsequivValidateurUser(cell.getStringCellValue());
                        case 11 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date date = (Date) cell.getDateCellValue();
                                deq.setDsequivDateValidateur(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateValidateur(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateValidateur(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 12 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                java.util.Date date = cell.getDateCellValue();
                                deq.setDsequivDateCreation(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateCreation(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateCreation(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 13 -> {
                            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                                double numericValue = cell.getNumericCellValue();
                                deq.setDsequivCouleurArtInit(Double.toString(numericValue));
                            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                                deq.setDsequivCouleurArtInit(cell.getStringCellValue());
                            }
                            break;
                        }
                        case 14 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                java.util.Date date = cell.getDateCellValue();
                                deq.setDsequivDateCouleurArtInit(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateCouleurArtInit(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateCouleurArtInit(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 15 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                java.util.Date date = cell.getDateCellValue();
                                deq.setDsequivDateLboArtInit(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateLboArtInit(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateLboArtInit(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 16 -> deq.setDsequivCouleurArtEq(cell.getStringCellValue());
                        case 17 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date date = (Date) cell.getDateCellValue();
                                deq.setDsequivDateCouleurArtEq(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateCouleurArtEq(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateCouleurArtEq(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 18 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date date = (Date) cell.getDateCellValue();
                                deq.setDsequivDateLboArtEq(new java.sql.Date(date.getTime()));
                            } else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue() != null) {
                                deq.setDsequivDateLboArtEq(Date.valueOf(LocalDate.now()));
                            } else {
                                deq.setDsequivDateLboArtEq(Date.valueOf(LocalDate.now()));
                            }
                            break;
                        }
                        case 19 -> deq.setDsequivCommentairesDemandeur(cell.getStringCellValue());
                        case 20 -> deq.setDsequivCommentairesValidateur(cell.getStringCellValue());
                        case 21 -> deq.setAppOwner(cell.getStringCellValue());
                        case 22 -> deq.setDsequivFollowedComponent(cell.getStringCellValue());
                        case 23 -> deq.setDsequivFollowedComponentEquiv(cell.getStringCellValue());
                        case 24 -> deq.setDsequivEnAttenteValidation(cell.getStringCellValue());
                        case 25 -> deq.setDsequivClients(cell.getStringCellValue());
                        default -> {
                        }
                    }
                        cellIndex++;
                    }
                // Check if DEQ with reference number already exists
                Optional<DossierEquivalence> existingDEQ = existingDeqs.stream()
                        .filter(d -> d.getDsequivReference().equals(deq.getDsequivReference()))
                        .findFirst();
                if (existingDEQ.isPresent()) {
                    // Update existing DEQ
                    DossierEquivalence updatedDEQ = existingDEQ.get();
                    updatedDEQ.setDsequivReference(deq.getDsequivReference());
                    updatedDEQ.setDsequivNiveauValidation(deq.getDsequivNiveauValidation());
                    updatedDEQ.setDsequivDateNiveauValidation(deq.getDsequivDateNiveauValidation());
                    updatedDEQ.setDsequivLienFichierInitial(deq.getDsequivLienFichierInitial());
                    updatedDEQ.setDsequivLienFichierFinal(deq.getDsequivLienFichierFinal());
                    updatedDEQ.setDsequivLienFichierComparatif(deq.getDsequivLienFichierComparatif());
                    updatedDEQ.setDsequivDemandeurUser(deq.getDsequivDemandeurUser());
                    updatedDEQ.setDsequivDateDemandeur(deq.getDsequivDateDemandeur());
                    updatedDEQ.setDsequivValidateurUser(deq.getDsequivValidateurUser());
                    updatedDEQ.setDsequivDateValidateur(deq.getDsequivDateValidateur());
                    updatedDEQ.setDsequivDateCreation(deq.getDsequivDateCreation());
                    updatedDEQ.setDsequivCouleurArtInit(deq.getDsequivCouleurArtInit());
                    updatedDEQ.setDsequivDateCouleurArtInit(deq.getDsequivDateCouleurArtInit());
                    updatedDEQ.setDsequivDateLboArtInit(deq.getDsequivDateLboArtInit());
                    updatedDEQ.setDsequivCouleurArtEq(deq.getDsequivCouleurArtEq());
                    updatedDEQ.setDsequivDateCouleurArtEq(deq.getDsequivDateCouleurArtEq());
                    updatedDEQ.setDsequivDateLboArtEq(deq.getDsequivDateLboArtEq());
                    updatedDEQ.setDsequivCommentairesDemandeur(deq.getDsequivCommentairesDemandeur());
                    updatedDEQ.setDsequivCommentairesValidateur(deq.getDsequivCommentairesValidateur());
                    updatedDEQ.setAppOwner(deq.getAppOwner());
                    updatedDEQ.setDsequivFollowedComponent(deq.getDsequivFollowedComponent());
                }
                deqs.add(deq);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return deqs;
    }
}
