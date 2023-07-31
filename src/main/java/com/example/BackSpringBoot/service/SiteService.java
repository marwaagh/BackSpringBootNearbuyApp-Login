package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.Client;
import com.example.BackSpringBoot.model.Site;
import com.example.BackSpringBoot.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Site> getSiteByClientSiteId(Long clientSiteId) {
        return siteRepository.findSiteByClientSiteId(clientSiteId);
    }
}
