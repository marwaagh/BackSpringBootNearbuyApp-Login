package com.example.BackSpringBoot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;

    private String appOwner;
    private String cltReference;
    private String cltNom;
    private String cltCodeMovex;
    private String cltNomContact;
    private String cltWebsite;
    private String cltTel;
    private String cltFax;
    private String cltAdresse;
    private String cltCpVille;
    private String cltEmail;
    private String cltLogoPath;
}
