package com.example.BackSpringBoot.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class DossierHomologation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String dshReference;
    private String dshNiveauValidation;
    private Date dshDateNiveauValidation;
    private String dshLienCourierFournisseur;
    private Date dshDateEnvoiDossier;
    private Date dshDateBasculement;
    private Date dshDateStockage;
    private String dshQteDisponible;
    private String dshRemarquesClient;
    @CreationTimestamp
    private Date dshDateCreation;
    private String dshDemandeurUser;
    private Date dshDemandeurDate;
    private String dshLienFichierReponseClient;
    private String dshLienFichierDemande;
    private String appOwner;
    private Boolean dshDemandeRex;
    private Long dshQteRex;
    private String dshFollowedComponent;
    private String dshFollowedComponentEquiv;
    private String dshEnAttenteEnvoi;
    private String dshArtInitInCmde;
    private String dshArtEquivInCmde;
    @ManyToOne
    private DossierEquivalence pkEquivalence;

    /* EN RELATION MANY TO ONE AVEC CLIENT SITE
     * @ManyToOne
     * private ClientSite pkClientSite;
     */
    @ManyToOne
    private DossierHomologation pkDossierHomologationOrigine;

}

