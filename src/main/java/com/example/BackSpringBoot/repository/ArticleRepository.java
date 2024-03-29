package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findById(Long id);
    List<Article> findAllByArtTypeArticle(String artTypeArticle);
    void deleteArticleById(Long id);
}

