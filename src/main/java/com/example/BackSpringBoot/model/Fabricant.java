package com.example.BackSpringBoot.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fabricant")
public class Fabricant{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ts")
    private Timestamp ts;
    @Column(name = "AppOwner")
    private String appowner;
    public String fbcReference;
    private String fbcNom;
    private String fbcWebsite;
    private String fbcNomContact;
    private String fbcTel;
    private String fbcFax;
    private String fbcPays;
    private Date fbcDateActualisation;
    private String fbcAdresse;
    private String fbcCpville;
    private String fbcEmail;
    private boolean fbcEstOrganismeTest;
    private boolean fbcEstOrganismeNormatif;
    private Date fbcDateCreation;
    private boolean fbcEstEnActivite;
    private String fbcCodeMovex;
    private String fbcBd1;
    private String fbcCodeFabTpp30j;
    private boolean fbcCritique;
    private String fbcNCAGE;
    private String fbcLienFichier;



    public String getFbcReference() {
        return fbcReference;
    }

    public void setFbcReference(String fbcReference) {
        this.fbcReference = fbcReference;
    }

    public String getFbcNom() {
        return fbcNom;
    }

    public void setFbcNom(String fbcNom) {
        this.fbcNom = fbcNom;
    }

    public String getFbcWebsite() {
        return fbcWebsite;
    }

    public void setFbcWebsite(String fbcWebsite) {
        this.fbcWebsite = fbcWebsite;
    }

    public String getFbcNomContact() {
        return fbcNomContact;
    }

    public void setFbcNomContact(String fbcNomContact) {
        this.fbcNomContact = fbcNomContact;
    }

    public String getFbcTel() {
        return fbcTel;
    }

    public void setFbcTel(String fbcTel) {
        this.fbcTel = fbcTel;
    }

    public String getFbcFax() {
        return fbcFax;
    }

    public void setFbcFax(String fbcFax) {
        this.fbcFax = fbcFax;
    }

    public String getFbcPays() {
        return fbcPays;
    }

    public void setFbcPays(String fbcPays) {
        this.fbcPays = fbcPays;
    }

    public Date getFbcDateActualisation() {
        return fbcDateActualisation;
    }

    public void setFbcDateActualisation(Date fbcDateActualisation) {
        this.fbcDateActualisation = fbcDateActualisation;
    }

    public String getFbcAdresse() {
        return fbcAdresse;
    }

    public void setFbcAdresse(String fbcAdresse) {
        this.fbcAdresse = fbcAdresse;
    }

    public String getFbcCpville() {
        return fbcCpville;
    }

    public void setFbcCpville(String fbcCpville) {
        this.fbcCpville = fbcCpville;
    }

    public String getFbcEmail() {
        return fbcEmail;
    }

    public void setFbcEmail(String fbcEmail) {
        this.fbcEmail = fbcEmail;
    }

    public boolean isFbcEstOrganismeTest() {
        return fbcEstOrganismeTest;
    }

    public void setFbcEstOrganismeTest(boolean fbcEstOrganismeTest) {
        this.fbcEstOrganismeTest = fbcEstOrganismeTest;
    }

    public boolean isFbcEstOrganismeNormatif() {
        return fbcEstOrganismeNormatif;
    }

    public void setFbcEstOrganismeNormatif(boolean fbcEstOrganismeNormatif) {
        this.fbcEstOrganismeNormatif = fbcEstOrganismeNormatif;
    }

    public Date getFbcDateCreation() {
        return fbcDateCreation;
    }

    public void setFbcDateCreation(Date fbcDateCreation) {
        this.fbcDateCreation = fbcDateCreation;
    }

    public boolean isFbcEstEnActivite() {
        return fbcEstEnActivite;
    }

    public void setFbcEstEnActivite(boolean fbcEstEnActivite) {
        this.fbcEstEnActivite = fbcEstEnActivite;
    }

    public String getFbcCodeMovex() {
        return fbcCodeMovex;
    }

    public void setFbcCodeMovex(String fbcCodeMovex) {
        this.fbcCodeMovex = fbcCodeMovex;
    }

    public String getFbcBd1() {
        return fbcBd1;
    }

    public void setFbcBd1(String fbcBd1) {
        this.fbcBd1 = fbcBd1;
    }

    public String getFbcCodeFabTpp30j() {
        return fbcCodeFabTpp30j;
    }

    public void setFbcCodeFabTpp30j(String fbcCodeFabTpp30j) {
        this.fbcCodeFabTpp30j = fbcCodeFabTpp30j;
    }

    public boolean isFbcCritique() {
        return fbcCritique;
    }

    public void setFbcCritique(boolean fbcCritique) {
        this.fbcCritique = fbcCritique;
    }

    public String getFbcNCAGE() {
        return fbcNCAGE;
    }

    public void setFbcNCAGE(String fbcNCAGE) {
        this.fbcNCAGE = fbcNCAGE;
    }

    public String getFbcLienFichier() {
        return fbcLienFichier;
    }

    public void setFbcLienFichier(String fbcLienFichier) {
        this.fbcLienFichier = fbcLienFichier;
    }

    public Fabricant(String fbcReference, String fbcNom, String fbcWebsite, String fbcNomContact, String fbcTel, String fbcFax, String fbcPays, Date fbcDateActualisation, String fbcAdresse, String fbcCpville, String fbcEmail, boolean fbcEstOrganismeTest, boolean fbcEstOrganismeNormatif, Date fbcDateCreation, boolean fbcEstEnActivite, String fbcCodeMovex, String fbcBd1, String fbcCodeFabTpp30j, boolean fbcCritique, String fbcNCAGE, String fbcLienFichier) {
        this.fbcReference = fbcReference;
        this.fbcNom = fbcNom;
        this.fbcWebsite = fbcWebsite;
        this.fbcNomContact = fbcNomContact;
        this.fbcTel = fbcTel;
        this.fbcFax = fbcFax;
        this.fbcPays = fbcPays;
        this.fbcDateActualisation = fbcDateActualisation;
        this.fbcAdresse = fbcAdresse;
        this.fbcCpville = fbcCpville;
        this.fbcEmail = fbcEmail;
        this.fbcEstOrganismeTest = fbcEstOrganismeTest;
        this.fbcEstOrganismeNormatif = fbcEstOrganismeNormatif;
        this.fbcDateCreation = fbcDateCreation;
        this.fbcEstEnActivite = fbcEstEnActivite;
        this.fbcCodeMovex = fbcCodeMovex;
        this.fbcBd1 = fbcBd1;
        this.fbcCodeFabTpp30j = fbcCodeFabTpp30j;
        this.fbcCritique = fbcCritique;
        this.fbcNCAGE = fbcNCAGE;
        this.fbcLienFichier = fbcLienFichier;
    }
}
