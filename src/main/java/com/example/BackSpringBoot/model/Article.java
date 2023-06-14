package com.example.BackSpringBoot.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article{

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
    private String ausrName;
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


    public Article getArtSerie() {
        return artSerie;
    }

    public void setArtSerie(Article artSerie) {
        this.artSerie = artSerie;
    }
    public String getArtReference() {
        return artReference;
    }

    public void setArtReference(String artReference) {
        this.artReference = artReference;
    }

    public String getArtTypeArticle() {
        return artTypeArticle;
    }

    public void setArtTypeArticle(String artTypeArticle) {
        this.artTypeArticle = artTypeArticle;
    }

    public String getArtTypeComposant() {
        return artTypeComposant;
    }

    public void setArtTypeComposant(String artTypeComposant) {
        this.artTypeComposant = artTypeComposant;
    }

    public Long getArtCycleVie() {
        return artCycleVie;
    }

    public void setArtCycleVie(Long artCycleVie) {
        this.artCycleVie = artCycleVie;
    }

    public String getArtCouleur() {
        return artCouleur;
    }

    public void setArtCouleur(String artCouleur) {
        this.artCouleur = artCouleur;
    }

    public Date getArtDateCouleur() {
        return artDateCouleur;
    }

    public void setArtDateCouleur(Date artDateCouleur) {
        this.artDateCouleur = artDateCouleur;
    }

    public String getArtCouleurPrecedente() {
        return artCouleurPrecedente;
    }

    public void setArtCouleurPrecedente(String artCouleurPrecedente) {
        this.artCouleurPrecedente = artCouleurPrecedente;
    }

    public Date getArtDateCouleurPrecedente() {
        return artDateCouleurPrecedente;
    }

    public void setArtDateCouleurPrecedente(Date artDateCouleurPrecedente) {
        this.artDateCouleurPrecedente = artDateCouleurPrecedente;
    }

    public String getArtSourceChangementCouleur() {
        return artSourceChangementCouleur;
    }

    public void setArtSourceChangementCouleur(String artSourceChangementCouleur) {
        this.artSourceChangementCouleur = artSourceChangementCouleur;
    }

    public String getArtSource() {
        return artSource;
    }

    public void setArtSource(String artSource) {
        this.artSource = artSource;
    }

    public String getArtLienDocAvisObsolescence() {
        return artLienDocAvisObsolescence;
    }

    public void setArtLienDocAvisObsolescence(String artLienDocAvisObsolescence) {
        this.artLienDocAvisObsolescence = artLienDocAvisObsolescence;
    }

    public String getArtDesignation() {
        return artDesignation;
    }

    public void setArtDesignation(String artDesignation) {
        this.artDesignation = artDesignation;
    }

    public String getAusrName() {
        return ausrName;
    }

    public void setAusrName(String ausrName) {
        this.ausrName = ausrName;
    }

    public String getArtBoitier() {
        return artBoitier;
    }

    public void setArtBoitier(String artBoitier) {
        this.artBoitier = artBoitier;
    }

    public String getArtPins() {
        return artPins;
    }

    public void setArtPins(String artPins) {
        this.artPins = artPins;
    }

    public String getArtGenerique() {
        return artGenerique;
    }

    public void setArtGenerique(String artGenerique) {
        this.artGenerique = artGenerique;
    }

    public Long getArtCycleVieGenerique() {
        return artCycleVieGenerique;
    }

    public void setArtCycleVieGenerique(Long artCycleVieGenerique) {
        this.artCycleVieGenerique = artCycleVieGenerique;
    }

    public Long getArtEquivalentPotentiels() {
        return artEquivalentPotentiels;
    }

    public void setArtEquivalentPotentiels(Long artEquivalentPotentiels) {
        this.artEquivalentPotentiels = artEquivalentPotentiels;
    }

    public String getArtNcage() {
        return artNcage;
    }

    public void setArtNcage(String artNcage) {
        this.artNcage = artNcage;
    }

    public String getArtNno() {
        return artNno;
    }

    public void setArtNno(String artNno) {
        this.artNno = artNno;
    }

    public String getArtRohs() {
        return artRohs;
    }

    public void setArtRohs(String artRohs) {
        this.artRohs = artRohs;
    }

    public String getArtRohsMsl() {
        return artRohsMsl;
    }

    public void setArtRohsMsl(String artRohsMsl) {
        this.artRohsMsl = artRohsMsl;
    }

    public String getArtRohsPeakReflow() {
        return artRohsPeakReflow;
    }

    public void setArtRohsPeakReflow(String artRohsPeakReflow) {
        this.artRohsPeakReflow = artRohsPeakReflow;
    }

    public String getArtRohsFinishType() {
        return artRohsFinishType;
    }

    public void setArtRohsFinishType(String artRohsFinishType) {
        this.artRohsFinishType = artRohsFinishType;
    }
    public Date getArtDateCreation() {
        return artDateCreation;
    }
    public void setArtDateCreation(Date artDateCreation) {
        this.artDateCreation = artDateCreation;
    }
    public Date getArtLboDate() {
        return artLboDate;
    }
    public void setArtLboDate(Date artLboDate) {
        this.artLboDate = artLboDate;
    }
    public String getArtTechnologie() {
        return artTechnologie;
    }
    public void setArtTechnologie(String artTechnologie) {
        this.artTechnologie = artTechnologie;
    }
    public String getArtCodeConstructeur() {
        return artCodeConstructeur;
    }
    public void setArtCodeConstructeur(String artCodeConstructeur) {
        this.artCodeConstructeur = artCodeConstructeur;
    }
    public String getArtLeadFramePlating() {
        return artLeadFramePlating;
    }
    public void setArtLeadFramePlating(String artLeadFramePlating) {
        this.artLeadFramePlating = artLeadFramePlating;
    }
    public String getArtCarteSn() {
        return artCarteSn;
    }
    public void setArtCarteSn(String artCarteSn) {
        this.artCarteSn = artCarteSn;
    }
    public String getArtCarteIndice() {
        return artCarteIndice;
    }
    public void setArtCarteIndice(String artCarteIndice) {
        this.artCarteIndice = artCarteIndice;
    }
    public String getArtCarteVersion() {
        return artCarteVersion;
    }
    public void setArtCarteVersion(String artCarteVersion) {
        this.artCarteVersion = artCarteVersion;
    }
    public String getArtInformations() {
        return artInformations;
    }

    public void setArtInformations(String artInformations) {
        this.artInformations = artInformations;
    }

    public String getArtItar() {
        return artItar;
    }

    public void setArtItar(String artItar) {
        this.artItar = artItar;
    }

    public String getArtItarPays() {
        return artItarPays;
    }

    public void setArtItarPays(String artItarPays) {
        this.artItarPays = artItarPays;
    }

    public String getArtItarEccn() {
        return artItarEccn;
    }

    public void setArtItarEccn(String artItarEccn) {
        this.artItarEccn = artItarEccn;
    }

    public String getArtItarUsml() {
        return artItarUsml;
    }

    public void setArtItarUsml(String artItarUsml) {
        this.artItarUsml = artItarUsml;
    }

    public String getArtItarMde() {
        return artItarMde;
    }

    public void setArtItarMde(String artItarMde) {
        this.artItarMde = artItarMde;
    }

    public Date getArtItarDateMaj() {
        return artItarDateMaj;
    }

    public void setArtItarDateMaj(Date artItarDateMaj) {
        this.artItarDateMaj = artItarDateMaj;
    }

    public String getArtReachItemWeight() {
        return artReachItemWeight;
    }

    public void setArtReachItemWeight(String artReachItemWeight) {
        this.artReachItemWeight = artReachItemWeight;
    }

    public String getArtReachSvhcPresence() {
        return artReachSvhcPresence;
    }

    public void setArtReachSvhcPresence(String artReachSvhcPresence) {
        this.artReachSvhcPresence = artReachSvhcPresence;
    }

    public String getArtReachSvhcList() {
        return artReachSvhcList;
    }

    public void setArtReachSvhcList(String artReachSvhcList) {
        this.artReachSvhcList = artReachSvhcList;
    }

    public String getArtReachSource() {
        return artReachSource;
    }

    public void setArtReachSource(String artReachSource) {
        this.artReachSource = artReachSource;
    }

    public String getArtReachCasAccountedForWeight() {
        return artReachCasAccountedForWeight;
    }

    public void setArtReachCasAccountedForWeight(String artReachCasAccountedForWeight) {
        this.artReachCasAccountedForWeight = artReachCasAccountedForWeight;
    }

    public String getArtReachPdslPresence() {
        return artReachPdslPresence;
    }

    public void setArtReachPdslPresence(String artReachPdslPresence) {
        this.artReachPdslPresence = artReachPdslPresence;
    }

    public String getArtReachPdslList() {
        return artReachPdslList;
    }

    public void setArtReachPdslList(String artReachPdslList) {
        this.artReachPdslList = artReachPdslList;
    }

    public Date getArtReachDateMaj() {
        return artReachDateMaj;
    }

    public void setArtReachDateMaj(Date artReachDateMaj) {
        this.artReachDateMaj = artReachDateMaj;
    }

    public String getArtReachConflictMineral() {
        return artReachConflictMineral;
    }

    public void setArtReachConflictMineral(String artReachConflictMineral) {
        this.artReachConflictMineral = artReachConflictMineral;
    }

    public String getArtLienDatasheet() {
        return artLienDatasheet;
    }

    public void setArtLienDatasheet(String artLienDatasheet) {
        this.artLienDatasheet = artLienDatasheet;
    }

    public String getArtLienJustificatif() {
        return artLienJustificatif;
    }

    public void setArtLienJustificatif(String artLienJustificatif) {
        this.artLienJustificatif = artLienJustificatif;
    }

    public String getArtReachLienDisclosure() {
        return artReachLienDisclosure;
    }

    public void setArtReachLienDisclosure(String artReachLienDisclosure) {
        this.artReachLienDisclosure = artReachLienDisclosure;
    }

    public String getArtReachLienCOFC() {
        return artReachLienCOFC;
    }

    public void setArtReachLienCOFC(String artReachLienCOFC) {
        this.artReachLienCOFC = artReachLienCOFC;
    }

    public String getArtReachLienEICCTemplate() {
        return artReachLienEICCTemplate;
    }

    public void setArtReachLienEICCTemplate(String artReachLienEICCTemplate) {
        this.artReachLienEICCTemplate = artReachLienEICCTemplate;
    }

    public String getArtNRFND() {
        return artNRFND;
    }

    public void setArtNRFND(String artNRFND) {
        this.artNRFND = artNRFND;
    }

    public String getArtItarManufacturerRemarks() {
        return artItarManufacturerRemarks;
    }

    public void setArtItarManufacturerRemarks(String artItarManufacturerRemarks) {
        this.artItarManufacturerRemarks = artItarManufacturerRemarks;
    }

    public String getArtItarIhsRemarks() {
        return artItarIhsRemarks;
    }

    public void setArtItarIhsRemarks(String artItarIhsRemarks) {
        this.artItarIhsRemarks = artItarIhsRemarks;
    }

    public String getArtItarAutre() {
        return artItarAutre;
    }

    public void setArtItarAutre(String artItarAutre) {
        this.artItarAutre = artItarAutre;
    }

    public String getArtItarClassificationEU() {
        return artItarClassificationEU;
    }

    public void setArtItarClassificationEU(String artItarClassificationEU) {
        this.artItarClassificationEU = artItarClassificationEU;
    }

    public String getArtFrequenceConsultation() {
        return artFrequenceConsultation;
    }

    public void setArtFrequenceConsultation(String artFrequenceConsultation) {
        this.artFrequenceConsultation = artFrequenceConsultation;
    }

    public String getArtReferenceMaAERO() {
        return artReferenceMaAERO;
    }

    public void setArtReferenceMaAERO(String artReferenceMaAERO) {
        this.artReferenceMaAERO = artReferenceMaAERO;
    }

    public String getArtDiametre() {
        return artDiametre;
    }

    public void setArtDiametre(String artDiametre) {
        this.artDiametre = artDiametre;
    }

    public String getArtLongueur() {
        return artLongueur;
    }

    public void setArtLongueur(String artLongueur) {
        this.artLongueur = artLongueur;
    }

    public String getArtReferenceInitiale() {
        return artReferenceInitiale;
    }

    public void setArtReferenceInitiale(String artReferenceInitiale) {
        this.artReferenceInitiale = artReferenceInitiale;
    }

    public String getArtCertificatDeConformite() {
        return artCertificatDeConformite;
    }

    public void setArtCertificatDeConformite(String artCertificatDeConformite) {
        this.artCertificatDeConformite = artCertificatDeConformite;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Norme getNorme() {
        return norme;
    }

    public void setNorme(Norme norme) {
        this.norme = norme;
    }

    public FamilleComposant getFamilleComposant() {
        return familleComposant;
    }

    public void setFamilleComposant(FamilleComposant familleComposant) {
        this.familleComposant = familleComposant;
    }

    public CodeINC getCodeINC() {
        return codeINC;
    }

    public void setCodeINC(CodeINC codeINC) {
        this.codeINC = codeINC;
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
        this.artTypeComposant = artTypeComposant;
        this.artCycleVie = artCycleVie;
        this.artCouleur = artCouleur;
        this.artDateCouleur = artDateCouleur;
        this.artCouleurPrecedente = artCouleurPrecedente;
        this.artDateCouleurPrecedente = artDateCouleurPrecedente;
        this.artSourceChangementCouleur = artSourceChangementCouleur;
        this.artSource = artSource;
        this.artLienDocAvisObsolescence = artLienDocAvisObsolescence;
        this.artDesignation = artDesignation;
        this.ausrName = ausrName;
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
