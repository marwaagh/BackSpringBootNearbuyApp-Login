package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.model.SiteFctSys;
import com.example.BackSpringBoot.repository.SiteFctSysRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteFctSysService {
    private final SiteFctSysRepository siteFctSysRepository;

    public SiteFctSysService(SiteFctSysRepository siteFctSysRepository) {
        this.siteFctSysRepository = siteFctSysRepository;
    }

    public List<SiteFctSys> findAllSiteFctSys(){
        return siteFctSysRepository.findAll();
    }

    public SiteFctSys addSiteFctSys(SiteFctSys siteFctSys) {
        return siteFctSysRepository.save(siteFctSys);
    }
}
