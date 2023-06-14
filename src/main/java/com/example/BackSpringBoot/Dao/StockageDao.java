package com.example.BackSpringBoot.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StockageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StockageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String callStockageFunction(long pkDossierHomologation, int quantiteDisponible, long pkCommandeClient,
                                                       long codeArticle, String appOwner) {
        try {
            String query = "SELECT public.stockage(?, ?, ?, ?, ?)";
            String returnValue = jdbcTemplate.queryForObject(query, String.class, pkDossierHomologation, quantiteDisponible,
                    pkCommandeClient, codeArticle, appOwner);

            if (returnValue.startsWith("article déjà stocké")) {
                // Handle the case when the article is already stocked
                return returnValue;
            } else {
                // Handle the case when the article is successfully stocked
                return returnValue;
            }
        } catch (DataAccessException ex) {
            String errorMessage = ex.getMessage();
            return errorMessage;
        }
    }
}

