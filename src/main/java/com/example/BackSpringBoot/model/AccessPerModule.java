package com.example.BackSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccessPerModule {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    @ManyToOne
    Access access;

    @ManyToOne
    Module module;


    public AccessPerModule(Long moduleId) {
        this.id = moduleId;
    }
}
