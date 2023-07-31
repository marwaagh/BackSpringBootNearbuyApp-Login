package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.appuser.AppUser;
import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.CodeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CodeArticleRepository extends JpaRepository<CodeArticle, Long> {
    List<CodeArticle> findAllByPkClientSite(ClientSite pkClientSite);
}
