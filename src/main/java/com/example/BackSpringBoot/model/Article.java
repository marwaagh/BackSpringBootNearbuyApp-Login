package com.example.BackSpringBoot.model;

import com.example.BackSpringBoot.LOVElements.LOVElement;
import com.example.BackSpringBoot.LOVElements.TypeComposant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ARTICLE")
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String artReference;
    private String artTypeArticle;
    private String artTypeComposant;
    private Long artCycleVie;
    private String artCouleur;
    private Date artDateCouleur;
    private String artCouleurPrecedente;
    private Date artDateCouleurPrecedente;
    private String artSourceChangementCouleur;
    private String artSource;
    private String artLienDocAvisObsolescence;
    private String artDesignation;
    private String appOwner;
    private String artBoitier;
    private String artPins;
    private String artGenerique;
    private Long artCycleVieGenerique;
    private Long artEquivalentPotentiels;
    private String artNcage;
    private String artNno;
    private String artRohs;
    private String artRohsMsl;
    private String artRohsPeakReflow;
    private String artRohsFinishType;
    private Date artDateCreation;
    private Date artLboDate;
    private String artTechnologie;
    private String artCodeConstructeur;
    private String artLeadFramePlating;
    private String artCarteSn;
    private String artCarteIndice;
    private String artCarteVersion;
    private String artInformations;
    private String artItar;
    private String artItarPays;
    private String artItarEccn;
    private String artItarUsml;
    private String artItarMde;
    private Date artItarDateMaj;
    private String artReachItemWeight;
    private String artReachSvhcPresence;
    private String artReachSvhcList;
    private String artReachSource;
    private String artReachCasAccountedForWeight;
    private String artReachPdslPresence;
    private String artReachPdslList;
    private Date artReachDateMaj;
    private String artReachConflictMineral;
    private String artLienDatasheet;
    private String artLienJustificatif;
    private String artReachLienDisclosure;
    private String artReachLienCOFC;
    private String artReachLienEICCTemplate;
    private String artNRFND;
    private String artItarManufacturerRemarks;
    private String artItarIhsRemarks;
    private String artItarAutre;
    private String artItarClassificationEU;
    private String artFrequenceConsultation;
    private String artReferenceMaAERO;
    private String artDiametre;
    private String artLongueur;
    private String artReferenceInitiale;
    private String artCertificatDeConformite;
    @Formula("(SELECT CASE WHEN COUNT(ln.pk_article_composant_id) = 0 THEN 'non' ELSE 'oui' END FROM article a, lignenomenclature ln, nomenclature n, sitefctsys sfs WHERE ln.pk_article_composant_id = a.id AND ln.lnom_est_perennise = 'true' AND ln.nomenclature_id = n.id AND n.nom_decision_perennite = 'c' AND n.site_fct_sys_id = sfs.id AND sfs.stfcsy_decision_perennite = 'p' LIMIT 1 )")
    private String artFollowedComponent;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "serie_id")
    private Article artSerie ;
    @ManyToOne
    @JoinColumn(name = "id_fabricant")
    private Fabricant fabricant;
    @ManyToOne
    @JoinColumn(name = "id_norme")
    private Norme norme;
    @ManyToOne
    @JoinColumn(name = "id_famillecomposant")
    private FamilleComposant familleComposant;
    @ManyToOne
    @JoinColumn(name = "id_codeINC")
    private CodeINC codeINC;

    /*public static ArrayList<Integer[]> getStatusCheckIntervals(String typeComposant) {
        ArrayList<Integer[]> checkIntervals = new ArrayList<Integer[]>();
        if(TypeComposant.ACTIF.key.equals(typeComposant)){
            checkIntervals.add(new Integer[]{10000, 30});
            checkIntervals.add(new Integer[]{29, 24});
            checkIntervals.add(new Integer[]{23, 18});
            checkIntervals.add(new Integer[]{17, 12});
            checkIntervals.add(new Integer[]{11, 6});
            checkIntervals.add(new Integer[]{5, 0});
        }else if(TypeComposant.PASSIF.key.equals(typeComposant) || TypeComposant.PARTICULIER.key.equals(typeComposant)){
            checkIntervals.add(new Integer[]{10000, 179});
            checkIntervals.add(new Integer[]{178, 144});
            checkIntervals.add(new Integer[]{143, 108});
            checkIntervals.add(new Integer[]{107, 72});
            checkIntervals.add(new Integer[]{71, 36});
            checkIntervals.add(new Integer[]{35, 0});
        }else if (TypeComposant.COTS.key.equals(typeComposant)){
            checkIntervals.add(new Integer[]{10000, 365});
            checkIntervals.add(new Integer[]{364, 144});
            checkIntervals.add(new Integer[]{143, 108});
            checkIntervals.add(new Integer[]{107, 72});
            checkIntervals.add(new Integer[]{71, 36});
            checkIntervals.add(new Integer[]{35, 0});
        }else if (TypeComposant.MECANIQUE.key.equals(typeComposant)){
            checkIntervals.add(new Integer[]{10000, 365});
            checkIntervals.add(new Integer[]{364, 144});
            checkIntervals.add(new Integer[]{143, 108});
            checkIntervals.add(new Integer[]{107, 72});
            checkIntervals.add(new Integer[]{71, 36});
            checkIntervals.add(new Integer[]{35, 0});
        }
        return checkIntervals;
    }*/

    public void setArtCouleur(String artCouleur) {
        this.artCouleurPrecedente = this.artCouleur;
        this.artDateCouleurPrecedente = this.artDateCouleur;
        this.artCouleur = artCouleur;
        this.artDateCouleur = new Date(); // Update artDatecouleur with current date/time
    }


    public Article(String artReference,
                   String artTypeArticle,
                   String artTypeComposant,
                   Long artCycleVie,
                   String artCouleur,
                   Date artDateCouleur,
                   String artCouleurPrecedente,
                   Date artDateCouleurPrecedente,
                   String artSourceChangementCouleur,
                   String artSource,
                   String artLienDocAvisObsolescence,
                   String artDesignation,
                   String ausrName,
                   String artBoitier,
                   String artPins,
                   String artGenerique,
                   Long artCycleVieGenerique,
                   Long artEquivalentPotentiels,
                   String artNcage,
                   String artNno,
                   String artRohs,
                   String artRohsMsl,
                   String artRohsPeakReflow,
                   String artRohsFinishType,
                   Date artDateCreation,
                   Date artLboDate,
                   String artTechnologie,
                   String artCodeConstructeur,
                   String artLeadFramePlating,
                   String artCarteSn,
                   String artCarteIndice,
                   String artCarteVersion,
                   String artInformations,
                   String artItar,
                   String artItarPays,
                   String artItarEccn,
                   String artItarUsml,
                   String artItarMde,
                   Date artItarDateMaj,
                   String artReachItemWeight,
                   String artReachSvhcPresence,
                   String artReachSvhcList,
                   String artReachSource,
                   String artReachCasAccountedForWeight,
                   String artReachPdslPresence,
                   String artReachPdslList,
                   Date artReachDateMaj,
                   String artReachConflictMineral,
                   String artLienDatasheet,
                   String artLienJustificatif,
                   String artReachLienDisclosure,
                   String artReachLienCOFC,
                   String artReachLienEICCTemplate,
                   String artNRFND,
                   String artItarManufacturerRemarks,
                   String artItarIhsRemarks,
                   String artItarAutre,
                   String artItarClassificationEU,
                   String artFrequenceConsultation,
                   String artReferenceMaAERO,
                   String artDiametre,
                   String artLongueur,
                   String artReferenceInitiale,
                   String artCertificatDeConformite,
                   EntityManager entityManager,
                   Fabricant fabricant,
                   Article artSerie,
                   FamilleComposant familleComposant) {
        this.artReference = artReference;
        this.artTypeArticle = artTypeArticle;
        this.artTypeComposant = TypeComposant.ACTIF.key;
        this.artCycleVie = artCycleVie;
        this.artCouleur = artCouleur;
        this.artDateCouleur = artDateCouleur;
        this.artCouleurPrecedente = artCouleurPrecedente;
        this.artDateCouleurPrecedente = artDateCouleurPrecedente;
        this.artSourceChangementCouleur = artSourceChangementCouleur;
        this.artSource = artSource;
        this.artLienDocAvisObsolescence = artLienDocAvisObsolescence;
        this.artDesignation = artDesignation;
        this.appOwner = ausrName;
        this.artBoitier = artBoitier;
        this.artPins = artPins;
        this.artGenerique = artGenerique;
        this.artCycleVieGenerique = artCycleVieGenerique;
        this.artEquivalentPotentiels = artEquivalentPotentiels;
        this.artNcage = artNcage;
        this.artNno = artNno;
        this.artRohs = artRohs;
        this.artRohsMsl = artRohsMsl;
        this.artRohsPeakReflow = artRohsPeakReflow;
        this.artRohsFinishType = artRohsFinishType;
        this.artDateCreation = artDateCreation;
        this.artLboDate = artLboDate;
        this.artTechnologie = artTechnologie;
        this.artCodeConstructeur = artCodeConstructeur;
        this.artLeadFramePlating = artLeadFramePlating;
        this.artCarteSn = artCarteSn;
        this.artCarteIndice = artCarteIndice;
        this.artCarteVersion = artCarteVersion;
        this.artInformations = artInformations;
        this.artItar = artItar;
        this.artItarPays = artItarPays;
        this.artItarEccn = artItarEccn;
        this.artItarUsml = artItarUsml;
        this.artItarMde = artItarMde;
        this.artItarDateMaj = artItarDateMaj;
        this.artReachItemWeight = artReachItemWeight;
        this.artReachSvhcPresence = artReachSvhcPresence;
        this.artReachSvhcList = artReachSvhcList;
        this.artReachSource = artReachSource;
        this.artReachCasAccountedForWeight = artReachCasAccountedForWeight;
        this.artReachPdslPresence = artReachPdslPresence;
        this.artReachPdslList = artReachPdslList;
        this.artReachDateMaj = artReachDateMaj;
        this.artReachConflictMineral = artReachConflictMineral;
        this.artLienDatasheet = artLienDatasheet;
        this.artLienJustificatif = artLienJustificatif;
        this.artReachLienDisclosure = artReachLienDisclosure;
        this.artReachLienCOFC = artReachLienCOFC;
        this.artReachLienEICCTemplate = artReachLienEICCTemplate;
        this.artNRFND = artNRFND;
        this.artItarManufacturerRemarks = artItarManufacturerRemarks;
        this.artItarIhsRemarks = artItarIhsRemarks;
        this.artItarAutre = artItarAutre;
        this.artItarClassificationEU = artItarClassificationEU;
        this.artFrequenceConsultation = artFrequenceConsultation;
        this.artReferenceMaAERO = artReferenceMaAERO;
        this.artDiametre = artDiametre;
        this.artLongueur = artLongueur;
        this.artReferenceInitiale = artReferenceInitiale;
        this.artCertificatDeConformite = artCertificatDeConformite;
        Query query = entityManager.createNativeQuery(
                "SELECT CASE WHEN COUNT(ln.pk_article_composant) = 0 THEN 'non' ELSE 'oui' END " +
                        "FROM ligne_nomenclature ln, nomenclature n, sitefctsys sfs " +
                        "WHERE ln.pk_article_composant = pk_article " +
                        "AND ln.lnom_est_perennise = 'true' " +
                        "AND ln.pk_nomenclature = n.pk_nomenclature " +
                        "AND n.nom_decision_perennite = 'c' " +
                        "AND n.pk_site_fct_sys = sfs.pk_site_fct_sys " +
                        "AND sfs.stfcsy_decision_perennite = 'p' " +
                        "LIMIT 1"
        );
        Object result = query.getSingleResult();
        this.artFollowedComponent = (result == null) ? null : result.toString();
        this.fabricant = fabricant;
        this.familleComposant = familleComposant ;
        this.artSerie = artSerie;
    }
}
