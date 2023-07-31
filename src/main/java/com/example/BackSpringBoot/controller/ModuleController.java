package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.Access;
import com.example.BackSpringBoot.model.Module;
import com.example.BackSpringBoot.repository.AccessRepository;
import com.example.BackSpringBoot.repository.ModuleRepository;
import com.example.BackSpringBoot.service.AccessService;
import com.example.BackSpringBoot.service.ArticleService;
import com.example.BackSpringBoot.service.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/ipersyst/module")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;
    private ModuleService moduleService;
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Module> addModule(@RequestBody Module module){
        Module newModule = moduleService.addModule(module);
        return new ResponseEntity<>(newModule, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable long id, @RequestBody Module module){
        Module existingModule = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("module does not exist with id: " + id));

        BeanUtils.copyProperties(module, existingModule, ArticleService.getNullPropertyNames(module));

        Module updatedModule = moduleRepository.save(existingModule);
        return new ResponseEntity<>(updatedModule, HttpStatus.OK);
    }
}
