package com.example.BackSpringBoot.repository;

import com.example.BackSpringBoot.model.AccessPerModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AccessPerModuleRepository  extends JpaRepository<AccessPerModule, Long> {
}

