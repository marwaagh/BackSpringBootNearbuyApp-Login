package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);
    void deleteClientById(Long id);
    @Query("SELECT c FROM ClientSite cs JOIN cs.pkClient c WHERE cs.id = :clientSiteId")
    Optional<Client> findClientByClientSiteId(@Param("clientSiteId") Long clientSiteId);
}
