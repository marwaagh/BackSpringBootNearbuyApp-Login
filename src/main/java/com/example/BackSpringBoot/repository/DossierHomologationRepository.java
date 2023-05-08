package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DossierHomologationRepository extends JpaRepository<DossierHomologation, Long> {
    Optional<DossierHomologation> findById(Long id);
    void deleteDossierHomologationById(Long id);
}
