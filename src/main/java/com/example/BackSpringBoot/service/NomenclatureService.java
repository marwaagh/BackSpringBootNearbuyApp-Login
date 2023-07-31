package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import com.example.BackSpringBoot.repository.NomenclatureRepository;
import com.example.BackSpringBoot.repository.SiteFctSysRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NomenclatureService {
    private final NomenclatureRepository nomenclatureRepository;
    private final SiteFctSysRepository siteFctSysRepository;
    private final ClientSiteRepository clientSiteRepository;

    public NomenclatureService(NomenclatureRepository nomenclatureRepository, SiteFctSysRepository siteFctSysRepository, ClientSiteRepository clientSiteRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.siteFctSysRepository = siteFctSysRepository;
        this.clientSiteRepository = clientSiteRepository;
    }

    public List<Nomenclature> findAllNomenclature(){
        return nomenclatureRepository.findAll();
    }

    public Nomenclature addNomenclature(Nomenclature nomenclature) {
        return nomenclatureRepository.save(nomenclature);
    }

    public List<Nomenclature> getNomenclaturesByClientSiteId(Long clientSiteId) {
        return nomenclatureRepository.findAllByClientSiteId(clientSiteId);
    }
}
