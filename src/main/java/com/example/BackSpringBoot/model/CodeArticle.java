package com.example.BackSpringBoot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class CodeArticle {

    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String renCodeArticleMovex;
    private String renCodeArticleClient;
    private String codartStatut;
    private boolean codartSuivi;
    private String codartNiveauCriticite;
    private Integer codartQteDispo;
    private String appOwner;
    //fiha fazet formula qui depend mn pk_composant
    private String codartFollowedComponent;
    //fiha fazet formula qui depend mn pk_composant
    private String codartArticleSuiviList;
    //fiha fazet formula qui depend mn pk_composant
    private String codartArticleNonSuiviList;

    // lclass hedhi commentée fi ipersyst !!!!!
    @ManyToOne
    private Article pkComposant ;
    @ManyToOne
    private ClientSite pkClientSite ;
}
