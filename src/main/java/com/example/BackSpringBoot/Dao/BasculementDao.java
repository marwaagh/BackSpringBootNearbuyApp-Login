package com.example.BackSpringBoot.Dao;

import com.example.BackSpringBoot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BasculementDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BasculementDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String callBasculementFunction(long pkDossierHomologation, String appOwner) {
        try {
       // Basculement
                    String query = "SELECT public.basculement(?, ?)";
                    return jdbcTemplate.queryForObject(query, String.class, pkDossierHomologation, appOwner);
                    /*
            //dsh liée
            String sql0 = "SELECT * FROM dossier_homologation WHERE id = ?";
            DossierHomologation theDsh = jdbcTemplate.queryForObject(sql0, DossierHomologation.class, pkDossierHomologation);
            //deq liée
            String sql1 = "SELECT * FROM dossierequivalence WHERE id = ?";
            DossierEquivalence theDeq = jdbcTemplate.queryForObject(sql1, DossierEquivalence.class, theDsh.getPkEquivalence());
            //client site lié
            String sql2 = "SELECT * FROM client_site WHERE id = ?";
            ClientSite theClst = jdbcTemplate.queryForObject(sql2, ClientSite.class, theDsh.getPkClientSite());
            //sitefctsys lié
            String sql3 = "SELECT * FROM sitefctsys WHERE pk_client_site_id = ?";
            SiteFctSys theSFS = jdbcTemplate.queryForObject(sql3, SiteFctSys.class, theClst.getId());
            //nomenclature liée
            String sql4 = "SELECT * FROM nomenclature WHERE site_fct_sys_id = ?";
            Nomenclature theNom = jdbcTemplate.queryForObject(sql4, Nomenclature.class, theSFS.getId());
            //liste des ligne nomenclatures liées

            String sql5 = "SELECT * FROM lignenomenclature WHERE nomenclature_id = ?";
            List<LigneNomenclature> ligneNomenclatures = jdbcTemplate.query(sql5, new Object[]{theNom.getId()}, (rs, rowNum) -> {
                LigneNomenclature ligneNomenclature = new LigneNomenclature();
                ligneNomenclature.setId((long) rs.getInt("id"));
                return ligneNomenclature;
            });

// Perform other operations on the ligneNomenclatures list
            for (LigneNomenclature ligneNomenclature : ligneNomenclatures) {
                // Perform actions based on the conditions
                if (theClst.isClstActif()
                        && theSFS.getStfcsyDecisionPerennite().equals('p')
                        && theNom.getNomDecisionPerennite().equals('c')
                        && ligneNomenclature.isLnomEstPerennise()
                        && !ligneNomenclature.isLnomNePasBasculer()
                        && ligneNomenclature.getPkArticleComposant().equals(theDeq.getPkArticleInitial())) {
                    // Basculement
                    String query = "SELECT public.basculement(?, ?)";
                    String result = jdbcTemplate.queryForObject(query, String.class, pkDossierHomologation, appOwner);
                    // Process the result if needed
                }
            }

            return "Basculement function called successfully";*/

        } catch (DataAccessException ex) {
            String errorMessage = ex.getMessage();
            return errorMessage;
        }
    }

}
