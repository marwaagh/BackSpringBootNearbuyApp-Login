package com.example.BackSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dossierequivalence")
public class DossierEquivalence {

    public static final String PROP_DSEQUIV_FOLLOWED_COMPONENT = "dsequivFollowedComponent";
    public static final String PROP_PK_ARTICLE_INITIAL = "pkArticleInitial";
    public static final String PROP_ID = "id";
    public static String Id;
    public static final String PROP_DSEQUIV_NIVEAU_VALIDATION = "dsequivNiveauValidation";
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String dsequivReference;
    private String dsequivNiveauValidation ;
    @CreationTimestamp
    private Date dsequivDateNiveauValidation;
    private String dsequivLienFichierInitial;
    private String dsequivLienFichierFinal;
    private String dsequivLienFichierComparatif;
    private String dsequivDemandeurUser;
    private Date dsequivDateDemandeur;
    private String dsequivValidateurUser;
    private Date dsequivDateValidateur;
    @CreationTimestamp
    private Date dsequivDateCreation;
    private String dsequivCouleurArtInit;
    private Date dsequivDateCouleurArtInit;
    private Date dsequivDateLboArtInit;
    private String dsequivCouleurArtEq;
    private Date dsequivDateCouleurArtEq;
    private Date dsequivDateLboArtEq;
    private String dsequivCommentairesDemandeur;
    private String dsequivCommentairesValidateur;
    private String appOwner;
    @Formula("(SELECT CASE WHEN COUNT( de.id ) = 0 THEN 'non' ELSE 'oui' END FROM dossierequivalence de, dossier_homologation dh, lignenomenclature ln, nomenclature n, sitefctsys sfs WHERE de.id = dh.pk_equivalence_id AND de.id_art_init = ln.pk_article_composant_id AND ln.lnom_est_perennise = 'true' AND ln.nomenclature_id = n.id AND n.nom_decision_perennite = 'c' AND n.site_fct_sys_id = sfs.id AND sfs.stfcsy_decision_perennite = 'p' LIMIT 1)")
    private String dsequivFollowedComponent;
    private String dsequivFollowedComponentEquiv;
    private String dsequivEnAttenteValidation;
    private String dsequivClients;
    @ManyToOne
    @JoinColumn(name = "id_art_init")
    private Article pkArticleInitial ;

    @ManyToOne
    @JoinColumn(name = "id_art_equiv")
    private Article pkArticleEquivalent ;


    @PostLoad
    @PostPersist
    private void generateDsequivReference() {
        if (this.dsequivNiveauValidation == null || this.dsequivNiveauValidation.isEmpty()) {
            this.dsequivNiveauValidation = "En attente proposition";
        }
        this.dsequivReference = String.format("DEQ0000%d", this.id);
    }
}
