package com.example.BackSpringBoot.service;



import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.UserGroup;
import com.example.BackSpringBoot.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupService(UserGroupRepository userGroupRepository){
        this.userGroupRepository= userGroupRepository;
    }

    public UserGroup adduserGroup(UserGroup userGroup){
        return userGroupRepository.save(userGroup);
    }
    public List<UserGroup> findallusergroups(){
        return userGroupRepository.findAll();
    }
    public UserGroup updateusergroup(UserGroup userGroup){
        return  userGroupRepository.save(userGroup) ;
    }
    public UserGroup findUsergroupById(Long id){
        return userGroupRepository.findUsergroupById(id).orElseThrow(()-> new UserNotFoundException("le group ayant l id" + id +"est introuvable")) ;
    }
    public void deleteusergroup(Long id){
        userGroupRepository.deleteUsergroupById(id);
    }
}
