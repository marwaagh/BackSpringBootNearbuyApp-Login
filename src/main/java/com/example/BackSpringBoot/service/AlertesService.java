package com.example.BackSpringBoot.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.example.BackSpringBoot.LOVElements.*;
import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.repository.ArticleRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlertesService {

    @Autowired
   private final ArticleRepository articleRepository;

    public AlertesService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    Session session = null;

    public HashMap getAlertes( HashMap clientHM ) throws Exception{
            HashMap resultsHM = new HashMap();
            try {
                Date majFromDate = (Date)clientHM.get( CommonKeys.ALERT_MODULE_CLIENT_DATE_FROM );

                Calendar calendar = Calendar.getInstance();
                calendar.setTime( majFromDate );
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                majFromDate = calendar.getTime();

            }
            catch( Exception e )
            {
                resultsHM.clear();
                resultsHM.put( CommonKeys.ALERT_MODULE_SERVER_ERROR_MSG, e.getMessage() );
            }


            return resultsHM;
        }

  /*  public String getStsCompChangedCount(Date majFromDate) {
        Integer count = articleRepository.getCompChangedCount(
                TypeArticle.COMPOSANT.key,
                OuiNonSans.OUI.key,
                (String) Article.PROP_ART_COULEUR_PRECEDENTE,
                majFromDate
        );
        return String.valueOf(count);
    }*/


    }
