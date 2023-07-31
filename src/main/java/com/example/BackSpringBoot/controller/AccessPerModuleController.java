package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.AccessPerModule;
import com.example.BackSpringBoot.model.Module;
import com.example.BackSpringBoot.repository.AccessPerModuleRepository;
import com.example.BackSpringBoot.repository.ModuleRepository;
import com.example.BackSpringBoot.service.AccessPerModuleService;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping(path = "api/v1/ipersyst/accesspermodule")
    public class AccessPerModuleController {

        @Autowired
        private AccessPerModuleRepository accessPerModuleRepository;
        private AccessPerModuleService accessPerModuleService;
        public AccessPerModuleController(AccessPerModuleService accessPerModuleService) {
            this.accessPerModuleService = accessPerModuleService;
        }
        @PostMapping("/add")
        @ResponseBody
        public ResponseEntity<AccessPerModule> addModule(@RequestBody AccessPerModule accessPerModule){
            AccessPerModule newModule = accessPerModuleService.addAccessPerModule(accessPerModule);
            return new ResponseEntity<>(newModule, HttpStatus.CREATED) ;
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<AccessPerModule> updateAccessPerModule(@PathVariable long id, @RequestBody AccessPerModule accessPerModule){
            AccessPerModule existingModule = accessPerModuleRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("module does not exist with id: " + id));

            BeanUtils.copyProperties(accessPerModule, existingModule, ArticleService.getNullPropertyNames(accessPerModule));

            AccessPerModule updatedModule = accessPerModuleRepository.save(existingModule);
            return new ResponseEntity<>(updatedModule, HttpStatus.OK);
        }
    }