package com.example.BackSpringBoot.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bon_travail_test")
public class BonTravailTest {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String bdtReference;
    private Long bdtQuantite;
    private String bdtObservations;
    private Date bdtDateEnvoi;
    private boolean bdtEstTestFonctionnel;
    private Date bdtDateRetourSouhaitee;
    private String bdtTempTest;
    private String bdtQteTemp1;
    private String bdtQteTemp2;
    private String bdtSpecifsParts;
    private String bdtNiveauValidation;
    private String bdtDemandeurUser;
    private Date bdtDemandeurDate;
    @CreationTimestamp
    private Date bdtDateCreation;
    private String bdtValidateurUser;
    private Date bdtValidateurDate;
    private Date bdtDateRetour;
    private String bdtRetReference;
    private Long bdtRetQteBonnes;
    private Long bdtRetQteMauvaise;
    private String bdtRetLienFichier;
    private boolean bdtRetEstValide;
    private String appOwner;
    private String bdtCommentaireLabo;
    private String bdtCommentaireValidateur;
    @Formula("(SELECT CASE WHEN COUNT( ln.pk_article_composant_id ) = 0 THEN 'non' ELSE 'oui' END\n" +
            "FROM lignenomenclature ln, nomenclature n, sitefctsys sfs, bon_travail_test bdt\n" +
            "WHERE ln.pk_article_composant_id = bdt.pk_article_initial\n" +
            "AND ln.lnom_est_perennise = 'true'\n" +
            "AND ln.nomenclature_id = n.id\n" +
            "AND n.nom_decision_perennite = 'c'\n" +
            "AND n.site_fct_sys_id = sfs.id\n" +
            "AND sfs.stfcsy_decision_perennite = 'p'\n" +
            "LIMIT 1)")
    private String bdtFollowedInitComponent;
    @Formula("(SELECT CASE WHEN COUNT( ln.pk_article_composant_id ) = 0 THEN 'non' ELSE 'oui' END\n" +
            "FROM lignenomenclature ln, nomenclature n, sitefctsys sfs, bon_travail_test bdt\n" +
            "WHERE ln.pk_article_composant_id = bdt.pk_article_equivalent\n" +
            "AND ln.lnom_est_perennise = 'true'\n" +
            "AND ln.nomenclature_id = n.id\n" +
            "AND n.nom_decision_perennite = 'c'\n" +
            "AND n.site_fct_sys_id = sfs.id\n" +
            "AND sfs.stfcsy_decision_perennite = 'p'\n" +
            "LIMIT 1)")
    private String bdtFollowedEquivComponent;
    @Formula("(SELECT CASE WHEN bdt.pk_article_initial= bdt.pk_article_equivalent THEN 'non' ELSE 'oui' END FROM bon_travail_test bdt LIMIT 1)")
    private String bdtDiffInitEquiv;
    @Formula("(SELECT CASE WHEN bdt.bdt_niveau_validation = 'attente retour' THEN 'non' ELSE 'oui' END FROM bon_travail_test bdt LIMIT 1)")
    private String bdtEnAttenteRetour;
    @ManyToOne
    @JoinColumn(name = "pk_article_carte")
    private Article pkArticleCarte;
    @ManyToOne
    @JoinColumn(name = "pk_dossier_homologation")
    private DossierHomologation pkDossierHomologation;
    @ManyToOne
    @JoinColumn(name = "pk_fabricant")
    private Fabricant pkFabricant;
    @ManyToOne
    @JoinColumn(name = "pk_article_initial")
    private Article pkArticleInitial;
    /* class ligne commande client
    @ManyToOne
    @JoinColumn(name = "pk_ligne_commande_client")
    private LigneCommandeClient pkLigneCommandeClient;
    */
    @ManyToOne
    @JoinColumn(name = "pk_article_equivalent")
    private Article pkArticleEquivalent;

    @PostLoad
    @PostPersist
    private void generateBdtReference() {
        if (this.bdtReference == null || this.bdtNiveauValidation.isEmpty()) {
            this.bdtNiveauValidation = "En attente envoi";
        }
        this.bdtReference = String.format("BDT0000%d", this.id);
    }
}
