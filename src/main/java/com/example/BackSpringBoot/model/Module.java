package com.example.BackSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    @Enumerated(EnumType.STRING)
    private ModuleName mdlName;
}
