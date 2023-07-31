package com.example.BackSpringBoot.service;


import com.example.BackSpringBoot.model.AccessPerModule;
import com.example.BackSpringBoot.repository.AccessPerModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessPerModuleService {
    private final AccessPerModuleRepository accessPerModuleRepository;

    @Autowired
    public AccessPerModuleService(AccessPerModuleRepository accessPerModuleRepository) {
        this.accessPerModuleRepository = accessPerModuleRepository;
    }

    public AccessPerModule addAccessPerModule(AccessPerModule accessPerModule){
        return accessPerModuleRepository.save(accessPerModule);
    }

}
