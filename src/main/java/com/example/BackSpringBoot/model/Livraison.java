package com.example.BackSpringBoot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Livraison {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private Long livQuantite;
    private String livRefCmdeMovex;
    private String livRefNuLot;
    private String livRefNuExpeMovex;
    private Date livDateLiv;
    private String appOwner;
    @ManyToOne
    private LigneCommandeClient pkLigneCommandeClient ;
    @ManyToOne
    private Article pkArticleLiv ;
}
