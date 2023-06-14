package com.example.BackSpringBoot.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lignenomenclature")
public class LigneNomenclature {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private Date lnomDateCreation;
    private Long lnomQuantite;
    private Long lnomNumeroLigne;

    private String lnomNuLigneClient;

    private Date lnomDateRemplacement;

    private boolean lnomNePasBasculer;

    private Date lnomDateNePasBasculer;

    private boolean lnomEstPerennise;
    private String lnomCommentaires;

    private String appOwner;

    private String lnomInformationCreation;

    private String lnomCodeArtMovex;

    private String lnomCodeArtClient;

    private String lnomArtCritique;
    @ManyToOne
    private Nomenclature nomenclature;

    @ManyToOne
    private Article pkArticleComposant;

    @ManyToOne
    private LigneNomenclature ligneNomenclature;

    //Relation manytoone dossier de validation type dsh
    @ManyToOne
    private DossierHomologation pkDossierValidation;
}
