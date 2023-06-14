package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DossierEquivalenceRepository extends JpaRepository<DossierEquivalence, Long> {

    Optional<DossierEquivalence> findById(Long id);
    void deleteDossierEquivalenceById(Long id);
}
