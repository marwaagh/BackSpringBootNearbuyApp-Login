package com.example.BackSpringBoot.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "norme")
public class Norme{
    @Id
    @GeneratedValue
    private String id;
    @Column(name = "ts")
    private Timestamp ts;
    @Column(name = "AppOwner")
    private String appowner;
    private String nrmNorme;
    @ManyToOne
    private Fabricant fabricant;
    private String nrmTitre;
    private String nrmCommentaires;
    private String nrmLienFichier;
    private String nrmTypeNorme;
    @ManyToOne
    @JoinColumn(name = "id_norme")
    private Norme pkNormeMatiere;

    public String getNrmNorme() {
        return nrmNorme;
    }

    public void setNrmNorme(String nrmNorme) {
        this.nrmNorme = nrmNorme;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public String getNrmTitre() {
        return nrmTitre;
    }

    public void setNrmTitre(String nrmTitre) {
        this.nrmTitre = nrmTitre;
    }

    public String getNrmCommentaires() {
        return nrmCommentaires;
    }

    public void setNrmCommentaires(String nrmCommentaires) {
        this.nrmCommentaires = nrmCommentaires;
    }

    public String getNrmLienFichier() {
        return nrmLienFichier;
    }

    public void setNrmLienFichier(String nrmLienFichier) {
        this.nrmLienFichier = nrmLienFichier;
    }

    public String getNrmTypeNorme() {
        return nrmTypeNorme;
    }

    public void setNrmTypeNorme(String nrmTypeNorme) {
        this.nrmTypeNorme = nrmTypeNorme;
    }

    public Norme getPkNormeMatiere() {
        return pkNormeMatiere;
    }

    public void setPkNormeMatiere(Norme pkNormeMatiere) {
        this.pkNormeMatiere = pkNormeMatiere;
    }

    public Norme(String nrmNorme, Fabricant fabricant, String nrmTitre, String nrmCommentaires, String nrmLienFichier, String nrmTypeNorme, Norme pkNormeMatiere) {
        this.nrmNorme = nrmNorme;
        this.fabricant = fabricant;
        this.nrmTitre = nrmTitre;
        this.nrmCommentaires = nrmCommentaires;
        this.nrmLienFichier = nrmLienFichier;
        this.nrmTypeNorme = nrmTypeNorme;
        this.pkNormeMatiere = pkNormeMatiere;
    }
}
