package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article addArticle(Article article){
        return articleRepository.save(article);
    }
    public List<Article> findallArticles(){
        return articleRepository.findAll();
    }
    public Article updateArticle(Article article){
        return  articleRepository.save(article) ;
    }
    public Article findArticleById(Long id){
        return articleRepository.findById(id).orElseThrow(()-> new UserNotFoundException("l article ayant l id" + id +"est introuvable")) ;
    }
    /*  public List<Article> findArticlesByArtTypeArticle(String artTypeArticle) {
          List<Article> articles = articleRepository.findAllByArtTypeArticle(artTypeArticle);
          List<Article> filteredArticles = articles.stream()
                  .filter(article -> "serie".equals(article.getArtTypeArticle())
                          && article.getArtSerie() != null
                          && "serie".equals(article.getArtSerie().getArtTypeArticle()))
                  .collect(Collectors.toList());
          return filteredArticles;
      }*/
      public List<Article> findArticlesByArtTypeArticle(String artTypeArticle) {
          List<Article> articles = articleRepository.findAllByArtTypeArticle(artTypeArticle);
          if (articles.isEmpty()) {
              throw new UserNotFoundException("Aucun article ayant le type " + artTypeArticle + " n'a été trouvé.");
          }
          return articles;
      }
    public void deleteArticle(Long id){
        articleRepository.deleteArticleById(id);
    }

}
