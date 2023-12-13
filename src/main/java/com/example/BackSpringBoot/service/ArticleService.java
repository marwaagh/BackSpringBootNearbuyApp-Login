package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.ArticleRepository;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import com.example.BackSpringBoot.repository.DossierEquivalenceRepository;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ClientSiteRepository clientSiteRepository;
    private final DossierHomologationRepository dossierHomologationRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ClientSiteRepository clientSiteRepository, DossierEquivalenceRepository dossierEquivalenceRepository, DossierHomologationRepository dossierHomologationRepository) {
        this.articleRepository = articleRepository;
        this.clientSiteRepository = clientSiteRepository;
        this.dossierHomologationRepository = dossierHomologationRepository;
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

    //global function for all classes
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || pd.getName().equals("id")) { // Exclude 'id' property
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }


    public List<Article> getArticlesByClientSiteId(Long clientSiteId) {
        // Fetch the ClientSite object based on the provided clientSiteId
        ClientSite clientSite = clientSiteRepository.findById(clientSiteId)
                .orElseThrow(() -> new RuntimeException("ClientSite not found with ID: " + clientSiteId));

        // Use the repository method to find all DossierHomologation associated with the ClientSite
        List<DossierHomologation> dossierHomologations = dossierHomologationRepository.findAllByPkClientSite(clientSite);

        // Now, we have the list of DossierHomologation entities associated with the ClientSite.
        // We can iterate through them and collect the associated DossierEquivalence entities.

        List<DossierEquivalence> dossierEquivalences = dossierHomologations.stream()
                .map(DossierHomologation::getPkEquivalence)
                .collect(Collectors.toList());

        List<Article> articles = dossierEquivalences.stream()
                .flatMap(de -> Stream.of(de.getPkArticleInitial(), de.getPkArticleEquivalent()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return articles;
    }
}
