package com.example.BackSpringBoot.repository;


import com.example.BackSpringBoot.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserGroupRepository
        extends JpaRepository<UserGroup, Long> {

    Optional<UserGroup> findById(Long id);

    void deleteUsergroupById(Long id);

    Optional<UserGroup> findUsergroupById(Long id);
}