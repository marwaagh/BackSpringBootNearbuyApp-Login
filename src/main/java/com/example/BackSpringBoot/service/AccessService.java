package com.example.BackSpringBoot.service;


import com.example.BackSpringBoot.model.Access;
import com.example.BackSpringBoot.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccessService {
    private final AccessRepository accessRepository;

    @Autowired
    public AccessService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public Access addAccess(Access access){
        return accessRepository.save(access);
    }

}