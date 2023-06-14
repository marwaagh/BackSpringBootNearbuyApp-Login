package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Site;
import com.example.BackSpringBoot.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> findAll(){
        return siteRepository.findAll();
    }

    public Site addSite(Site site) {
        return siteRepository.save(site);
    }

    public void deleteSite(long id) {
        siteRepository.deleteById(id);
    }
}
