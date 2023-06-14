package com.example.BackSpringBoot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class LigneCommandeClient {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String lgcmdNumeroLigne;
    private Integer lgcmdQte;
    private Date lgcmdDateLivraisonSouhaitee;
    private boolean lgcmdCertificatConformite;
    private Double lgcmdPrixUnitaire;
    private String lgcmdCommentaires;
    private String appOwner;
    private Integer lgcmdDeliveredQty;
    private String lgcmdFollowedEquiv;
    private String lgcmdFollowedInit;
    private String lgcmdDiffInitEquiv;
    private String lgcmdDshExists;
    private String lgcmdDshRef;
    private String lgcmdDeqExists;
    private String lgcmdDeqRef;
    @ManyToOne
    private LigneCodeArticle pkLigneCodeArticleEquivalent ;
    @ManyToOne
    private LigneCodeArticle pkLigneCodeArticleInitial ;
    @ManyToOne
    private CommandeClient pkCommande ;
}
