package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {
    @Query("SELECT n FROM Nomenclature n WHERE n.siteFctSys.pkClientSite.id = :clientSiteId")
    List<Nomenclature> findAllByClientSiteId(@Param("clientSiteId") Long clientSiteId);
}
