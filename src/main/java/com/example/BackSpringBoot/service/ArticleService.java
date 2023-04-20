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

    /*Set DDV
    private boolean ruleDDV1On = true;
    public void applyDDV()  throws ResourceNotFoundException {
        if( this.ruleDDV1On ) {
            if ( this.getArtCouleur().equals( Couleur.VERT.key ) )
            {
                if ( !this.getArtTypeComposant().equals( TypeComposant.ACTIF.key ) )
                {
                    this.setArtCycleVie(new Long(5));
                    this.setArtCycleVieGenerique(new Long(10));
                }
                else
                {
                    this.setArtCycleVie(new Long(3));
                    this.setArtCycleVieGenerique(new Long(5));
                }
            }
            else if ( this.getArtCouleur().equals( Couleur.JAUNE.key ) )
            {
                this.setArtCycleVie(new Long(1));
                this.setArtCycleVieGenerique(new Long(1));
                this.setArtNRFND(NRFND.SANS.key);
            }
            else if ( this.getArtCouleur().equals( Couleur.ORANGE.key ) )
            {
                this.setArtCycleVie(new Long(2));
                this.setArtCycleVieGenerique(new Long(2));
            }
            else if(this.getArtCouleur().equals( Couleur.ROUGE.key ))
            {
                this.setArtCycleVie(new Long(0));
                this.setArtCycleVieGenerique(new Long(0));
                this.setArtNRFND(NRFND.SANS.key);
            }
            else if(this.getArtCouleur().equals( Couleur.SANS.key ))
            {
                this.setArtCycleVie(new Long(0));
                this.setArtCycleVieGenerique(new Long(0));
                this.setArtNRFND(NRFND.SANS.key);
            }
        }
        else
        {
            if ( this.getArtCouleur().equals( Couleur.VERT.key )
                    || this.getArtCouleur().equals( Couleur.JAUNE.key )
                    || this.getArtCouleur().equals( Couleur.ORANGE.key )
            )
            {
                if(  this.getArtCycleVie() < 1 )
                {
                    this.ruleDDV1On = true;
                    throw new GwtFrameworkException("DDV1 doit être > 0 !");
                }

                if( this.getArtCycleVieGenerique() < this.getArtCycleVie() )
                {
                    this.ruleDDV1On = true;
                    throw new GwtFrameworkException("DDV2 doit être >= DDV1 !");
                }
            }
            else if(this.getArtCouleur().equals( Couleur.ROUGE.key ))
            {
                this.setArtCycleVie(new Long(0));
                this.setArtCycleVieGenerique(new Long(0));
                this.setArtNRFND(NRFND.SANS.key);
            }
            else if(this.getArtCouleur().equals( Couleur.SANS.key ))
            {
                this.setArtCycleVie(new Long(0));
                this.setArtCycleVieGenerique(new Long(0));
                this.setArtNRFND(NRFND.SANS.key);
            }
        }

        this.ruleDDV1On = true;
    }*/

}
