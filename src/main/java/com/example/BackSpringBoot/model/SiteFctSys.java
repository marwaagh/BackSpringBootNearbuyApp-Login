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
@Table(name = "sitefctsys")
public class SiteFctSys {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String appOwner;
    private String stfcsyDecisionPerennite;
    private Date stfcsyDateDecisionPerennite;

    private boolean stfcsyEstValidee;

    private Date stfcsyDateValidation;
    private Long stfcsyQuantiteSysteme;

    private String stfcsyInformations;
    private String stfcsyLienDecision;

    //relation manytoone systeme
    @ManyToOne
    private ClientSite pkClientSite ;
    //relation manytoone fonction
}
