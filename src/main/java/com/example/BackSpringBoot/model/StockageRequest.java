package com.example.BackSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StockageRequest {
    private final DossierHomologation pkDossierHomologation;

    private final Integer dshQteDisponible;
    private final CommandeClient pkCommandeClient;
    private final CodeArticle pkCodeArticle ;
    private final String appOwner;
    private  final Date dshDateStockage;
}
