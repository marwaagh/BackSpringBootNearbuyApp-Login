package com.example.BackSpringBoot.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    private String usrgReference;
    private String usrgDescription;
    private String usrgLevel;
    private boolean usrgBlocked = false;

    public UserGroup(String usrgReference, String usrgDescription, String usrgLevel) {
        this.usrgReference = usrgReference ;
        this.usrgDescription = usrgDescription;
        this.usrgLevel = usrgLevel;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", ts=" + ts +
                ", usrgReference=" + usrgReference +
                ", usrgDescription='" + usrgDescription + '\'' +
                ", usrgLevel='" + usrgLevel + '\'' +
                ", usrgBlocked=" + usrgBlocked +
                '}';
    }

    public String getUsrgDescription() {
        return usrgDescription;
    }

    public void setUsrgDescription(String usrgDescription) {
        this.usrgDescription = usrgDescription;
    }

    public String getUsrgLevel() {
        return usrgLevel;
    }

    public void setUsrgLevel(String usrgLevel) {
        this.usrgLevel = usrgLevel;
    }

    public boolean isUsrgBlocked() {
        return usrgBlocked;
    }

    public void setUsrgBlocked(boolean usrgBlocked) {
        this.usrgBlocked = usrgBlocked;
    }
}