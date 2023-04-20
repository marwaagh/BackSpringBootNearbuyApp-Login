package com.example.BackSpringBoot.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "codeINC")
public class CodeINC{
    @Id
    @GeneratedValue
    private String id;
    @Column(name = "ts")
    private Timestamp ts;
    @Column(name = "AppOwner")
    private String appowner;
    private String pkClient;
    private String codeinccodeinc;
    private String codeincdesignation;
    private String codeincstatu;
    private String codeincdelimitation;

    public String getPkClient() {
        return pkClient;
    }

    public void setPkClient(String pkClient) {
        this.pkClient = pkClient;
    }

    public String getCodeinccodeinc() {
        return codeinccodeinc;
    }

    public void setCodeinccodeinc(String codeinccodeinc) {
        this.codeinccodeinc = codeinccodeinc;
    }

    public String getCodeincdesignation() {
        return codeincdesignation;
    }

    public void setCodeincdesignation(String codeincdesignation) {
        this.codeincdesignation = codeincdesignation;
    }

    public String getCodeincstatu() {
        return codeincstatu;
    }

    public void setCodeincstatu(String codeincstatu) {
        this.codeincstatu = codeincstatu;
    }

    public String getCodeincdelimitation() {
        return codeincdelimitation;
    }

    public void setCodeincdelimitation(String codeincdelimitation) {
        this.codeincdelimitation = codeincdelimitation;
    }

    public CodeINC(String pkClient, String codeinccodeinc, String codeincdesignation, String codeincstatu, String codeincdelimitation) {
        this.pkClient = pkClient;
        this.codeinccodeinc = codeinccodeinc;
        this.codeincdesignation = codeincdesignation;
        this.codeincstatu = codeincstatu;
        this.codeincdelimitation = codeincdelimitation;
    }
}
