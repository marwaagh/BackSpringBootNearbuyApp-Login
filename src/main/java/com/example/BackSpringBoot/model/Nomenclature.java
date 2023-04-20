package com.example.BackSpringBoot.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "nomenclature")
public class Nomenclature {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String nomReference;
    private boolean nomEstValidee = false;
    private Date nomDateCreation;
    private Long nomQuantiteListe;
    private String nomDecisionPerennite;

    private String nomInformations;

    private String nomLienDecision;

    private Date nomDateDecisionPerennite;

    private String appOwner;

    private Double nomPourcentagePerennite;
    private Long nomNombreReference;

    private Long nomNombreComposant;
    private Long nomNombreActif;

    private Long nomNombrePassif;
    private Long nomNombreParticulier;
    private Long nomNombreCots;
    private Long nomNombreMecanique;

    @ManyToOne
    private Article article;

    @ManyToOne
    private SiteFctSys siteFctSys;
}
