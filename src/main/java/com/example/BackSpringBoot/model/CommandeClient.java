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
public class CommandeClient {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String cmdReference;
    private String cmdNuPropal;
    private boolean cmdEstEnvoyeeDansMovex;
    private String cmdTitreCommande;
    private String cmdFichierCommande;
    private String cmdCommandeMovex;
    private boolean cmdEstAnnule;
    private Date cmdDateCommandeClient;
    private String cmdReferenceDevise;
    @CreationTimestamp
    private Date cmdDateCreation;
    private boolean cmdEstPreteExportMovex;
    private String cmdReferencePereneo;
    private String appOwner;
    private boolean cmdEstSoldee;
    @OneToOne
    private ClientSite pkClientSite ;
}
