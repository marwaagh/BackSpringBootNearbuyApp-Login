package com.example.BackSpringBoot.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "famillecomposant")
public class FamilleComposant{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "ts")
    private Timestamp ts;
    @Column(name = "AppOwner")
    private String appowner;
    private String famcmpReference;
    public String famcmpNom;
    private String famcmpTypeComposant;
    private String famcmpInfo;
    private String famcmpGroupeMovex;
    private boolean famcmpCompProg;

    public String getFamcmpReference() {
        return famcmpReference;
    }

    public void setFamcmpReference(String famcmpReference) {
        this.famcmpReference = famcmpReference;
    }

    public String getFamcmpNom() {
        return famcmpNom;
    }

    public void setFamcmpNom(String famcmpNom) {
        this.famcmpNom = famcmpNom;
    }

    public String getFamcmpTypeComposant() {
        return famcmpTypeComposant;
    }

    public void setFamcmpTypeComposant(String famcmpTypeComposant) {
        this.famcmpTypeComposant = famcmpTypeComposant;
    }

    public String getFamcmpInfo() {
        return famcmpInfo;
    }

    public void setFamcmpInfo(String famcmpInfo) {
        this.famcmpInfo = famcmpInfo;
    }

    public String getFamcmpGroupeMovex() {
        return famcmpGroupeMovex;
    }

    public void setFamcmpGroupeMovex(String famcmpGroupeMovex) {
        this.famcmpGroupeMovex = famcmpGroupeMovex;
    }

    public boolean isFamcmpCompProg() {
        return famcmpCompProg;
    }

    public void setFamcmpCompProg(boolean famcmpCompProg) {
        this.famcmpCompProg = famcmpCompProg;
    }

    public FamilleComposant(String famcmpReference, String famcmpNom, String famcmpTypeComposant, String famcmpInfo, String famcmpGroupeMovex, boolean famcmpCompProg) {
        this.famcmpReference = famcmpReference;
        this.famcmpNom = famcmpNom;
        this.famcmpTypeComposant = famcmpTypeComposant;
        this.famcmpInfo = famcmpInfo;
        this.famcmpGroupeMovex = famcmpGroupeMovex;
        this.famcmpCompProg = famcmpCompProg;
    }
}
