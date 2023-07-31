package com.example.BackSpringBoot.service;


import com.example.BackSpringBoot.model.Access;
import com.example.BackSpringBoot.model.Module;
import com.example.BackSpringBoot.repository.AccessRepository;
import com.example.BackSpringBoot.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Module addModule(Module module){
        return moduleRepository.save(module);
    }

}
