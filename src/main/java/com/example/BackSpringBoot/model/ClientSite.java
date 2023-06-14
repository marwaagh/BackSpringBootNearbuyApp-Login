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
@Table(name = "client_site")
public class ClientSite {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;

    private String appOwner;
    private String clstReference;
    private String clstReferenceMovex;
    private String clstContact;
    private String clstTel;
    private String clstIb;
    private String clstAdresse;
    private String clstCpville;
    private String clstFax;
    private String clstEmail;
    private String clstEmailDSH;
    private String clstWebSite;
    @CreationTimestamp
    private Date clstDateCreation;
    private boolean clstActif;
    private boolean clsRapportSpec;
    private String clstAdresseMovex;
    private String clstLogo;
    @ManyToOne
    private Client pkClient;
    @ManyToOne
    private Site pkSite;
}
