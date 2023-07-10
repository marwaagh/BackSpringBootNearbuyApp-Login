package com.example.BackSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    public static final String PROP_ID = "id";
    public static final String PROP_DSH_NIVEAU_VALIDATION = "dshNiveauValidation";
    public static final String PROP_PK_EQUIVALENCE = "pkEquivalence";
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
    @CreationTimestamp
    private Date dshDateNiveauValidation;
    private String dshLienCourierFournisseur;
    private Date dshDateEnvoiDossier;
    private Date dshDateBasculement;
    private Date dshDateStockage;
    private Integer dshQteDisponible;
    private String dshRemarquesClient;
    @CreationTimestamp
    private Date dshDateCreation;
    private String dshDemandeurUser;
    private Date dshDemandeurDate;
    private String dshLienFichierReponseClient;
    private String dshLienFichierDemande;
    private String appOwner;
    private Boolean dshDemandeRex = true;
    private Long dshQteRex;
    private String dshFollowedComponent;
    private String dshFollowedComponentEquiv;
    private String dshEnAttenteEnvoi;
    private String dshArtInitInCmde;
    private String dshArtEquivInCmde;
    @ManyToOne
    private DossierEquivalence pkEquivalence;
    @ManyToOne
    private ClientSite pkClientSite;
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinColumn(name = "pk_dossier_homologation_origine_id")
    private DossierHomologation pkDossierHomologationOrigine;

    @PostLoad
    @PostPersist
    private void generateDsequivReference() {
        if (this.dshNiveauValidation == null || this.dshNiveauValidation.isEmpty()) {
            this.dshNiveauValidation = "En attente envoi";
        }
        this.dshReference = String.format("DSH0000%d", this.id);
    }

}

