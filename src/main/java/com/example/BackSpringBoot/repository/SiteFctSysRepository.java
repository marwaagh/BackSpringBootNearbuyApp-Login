package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.model.SiteFctSys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface SiteFctSysRepository extends JpaRepository<SiteFctSys, Long> {
    List<SiteFctSys> findAllByPkClientSite(ClientSite pkClientSite);

}
