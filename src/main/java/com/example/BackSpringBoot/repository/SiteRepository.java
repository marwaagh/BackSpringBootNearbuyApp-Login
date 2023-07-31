package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SiteRepository extends JpaRepository<Site, Long> {

    @Query("SELECT s FROM ClientSite cs JOIN cs.pkSite s WHERE cs.id = :clientSiteId")
    Optional<Site> findSiteByClientSiteId(@Param("clientSiteId") Long clientSiteId);
}
