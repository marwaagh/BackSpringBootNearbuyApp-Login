package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DossierEquivalenceService {
    private final DossierEquivalenceRepository dossierEquivalenceRepository;
    private final DossierHomologationRepository dossierHomologationRepository;
    private final ClientSiteRepository clientSiteRepository;

    @Autowired
    public DossierEquivalenceService(DossierEquivalenceRepository dossierEquivalenceRepository, DossierHomologationRepository dossierHomologationRepository, ClientSiteRepository clientSiteRepository) {
        this.dossierEquivalenceRepository = dossierEquivalenceRepository;
        this.dossierHomologationRepository = dossierHomologationRepository;
        this.clientSiteRepository = clientSiteRepository;
    }

    public List<DossierEquivalence> addDossierEquivalencesFromExcelFile(MultipartFile file) throws IOException {
        List<DossierEquivalence> deqs = getDEQDataFromExcel(file.getInputStream());
        if (ExcelUploadService.isValidExcelFile(file)) {
            deqs = ExcelUploadService.getDEQDataFromExcel(file.getInputStream(), dossierEquivalenceRepository);
            return dossierEquivalenceRepository.saveAll(deqs);
        }
        return deqs;
    }

    public DossierEquivalence addDossierEquivalence(DossierEquivalence dossierEquivalence) {
        return dossierEquivalenceRepository.save(dossierEquivalence);
    }

    public List<DossierEquivalence> findallDossierEquivalences() {
        return dossierEquivalenceRepository.findAll();
    }

    public DossierEquivalence updateDossierEquivalence(DossierEquivalence dossierEquivalence) {
        return dossierEquivalenceRepository.save(dossierEquivalence);
    }

    public DossierEquivalence findDossierEquivalenceById(Long id) {
        return dossierEquivalenceRepository.findById(id).orElseThrow(() -> new UserNotFoundException("le dossier equivalence ayant l id" + id + "est introuvable"));
    }

    public void deleteDossierEquivalence(Long id) {
        dossierEquivalenceRepository.deleteDossierEquivalenceById(id);
    }

    public List<DossierEquivalence> getDEQDataFromExcel(InputStream inputStream) throws IOException {
        List<DossierEquivalence> deqs = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("deqs");
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            DossierEquivalence deq = new DossierEquivalence();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                String cellValue = dataFormatter.formatCellValue(cell);

                switch (columnIndex) {
                    case 0:
                        deq.setId((long) Double.parseDouble(cellValue));
                        break;
                    case 1:
                        deq.setTs(Timestamp.valueOf(cellValue));
                        break;
                    case 2:
                        deq.setDsequivReference(cellValue);
                        break;
                    case 3:
                        deq.setDsequivNiveauValidation(cellValue);
                        break;
                    case 4:
                        deq.setDsequivDateNiveauValidation((Date) cell.getDateCellValue());
                        break;
                    case 5:
                        deq.setDsequivLienFichierInitial(cellValue);
                        break;
                    case 6:
                        deq.setDsequivLienFichierFinal(cellValue);
                        break;
                    case 7:
                        deq.setDsequivLienFichierComparatif(cellValue);
                        break;
                    case 8:
                        deq.setDsequivDemandeurUser(cellValue);
                        break;
                    case 9:
                        deq.setDsequivDateDemandeur((Date) cell.getDateCellValue());
                        break;
                    case 10:
                        deq.setDsequivValidateurUser(cellValue);
                        break;
                    case 11:
                        deq.setDsequivDateValidateur((Date) cell.getDateCellValue());
                        break;
                    case 12:
                        deq.setDsequivDateCreation((Date) cell.getDateCellValue());
                        break;
                    case 13:
                        deq.setDsequivCouleurArtInit(cellValue);
                        break;
                    case 14:
                        deq.setDsequivDateCouleurArtInit((Date) cell.getDateCellValue());
                        break;
                    case 15:
                        deq.setDsequivDateLboArtInit((Date) cell.getDateCellValue());
                        break;
                    case 16:
                        deq.setDsequivCouleurArtEq(cellValue);
                        break;
                    case 17:
                        deq.setDsequivDateCouleurArtEq((Date) cell.getDateCellValue());
                        break;
                    case 18:
                        deq.setDsequivDateLboArtEq((Date) cell.getDateCellValue());
                        break;
                    case 19:
                        deq.setDsequivCommentairesDemandeur(cellValue);
                        break;
                    case 20:
                        deq.setDsequivCommentairesValidateur(cellValue);
                        break;
                    case 21:
                        deq.setAppOwner(cellValue);
                        break;
                    case 22:
                        deq.setDsequivFollowedComponent(cellValue);
                        break;
                    case 23:
                        deq.setDsequivFollowedComponentEquiv(cellValue);
                        break;
                    case 24:
                        deq.setDsequivEnAttenteValidation(cellValue);
                        break;
                    case 25:
                        deq.setDsequivClients(cellValue);
                        break;
                    default:
                        //

                }
            }
            deqs.add(deq);
        }
        return deqs;
    }

    public List<DossierEquivalence> getDossierEquivalencesByClientSiteId(Long clientSiteId) {
        // Fetch the ClientSite object based on the provided clientSiteId
        ClientSite clientSite = clientSiteRepository.findById(clientSiteId)
                .orElseThrow(() -> new RuntimeException("ClientSite not found with ID: " + clientSiteId));

        // Use the repository method to find all DossierHomologation associated with the ClientSite
        List<DossierHomologation> dossierHomologations = dossierHomologationRepository.findAllByPkClientSite(clientSite);

        // Now, we have the list of DossierHomologation entities associated with the ClientSite.
        // We can iterate through them and collect the associated DossierEquivalence entities.

        return dossierHomologations.stream()
                .map(DossierHomologation::getPkEquivalence)
                .collect(Collectors.toList());
    }

}
