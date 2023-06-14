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
public class LigneCodeArticle {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    @CreationTimestamp
    private Date lgcaDateCreation;
    private boolean lgcaSuivi;
    private String lgcaNiveauObso;
    private String appOwner;
    @ManyToOne
    private CodeArticle pkCodeArticle ;
    @ManyToOne
    private Article pkArticle ;
}
