package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.UserGroup;
import com.example.BackSpringBoot.repository.UserGroupRepository;
import com.example.BackSpringBoot.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/usergroup")
public class UserGroupController {
    private final UserGroupService userGroupService;
    @Autowired
    private UserGroupRepository userGroupRepository;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserGroup>> getallUserGroups(){
        List<UserGroup> userGroups = userGroupService.findallusergroups();
        return new ResponseEntity<>(userGroups, HttpStatus.OK) ;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserGroup> getUserGroupById(@PathVariable("id") Long id){
        UserGroup userGroup = userGroupService.findUsergroupById(id);
        return new ResponseEntity<>(userGroup, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<UserGroup> addusergroup(@RequestBody UserGroup userGroup){
        UserGroup newUserGroup = userGroupService.adduserGroup(userGroup);
        return new ResponseEntity<>(newUserGroup, HttpStatus.CREATED) ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserGroup> updateusergroup(@PathVariable long id, @RequestBody UserGroup userGroup){
        UserGroup updateusergroup = userGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" user group not exist with id: " + id));
        updateusergroup.setUsrgReference(userGroup.getUsrgReference());
        updateusergroup.setUsrgDescription(userGroup.getUsrgDescription());
        updateusergroup.setUsrgLevel(userGroup.getUsrgLevel());
        updateusergroup.setUsrgBlocked(userGroup.isUsrgBlocked());

        userGroupRepository.save(updateusergroup) ;
        return new ResponseEntity<>(updateusergroup, HttpStatus.OK) ;
           // return "user updated !!!!";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteusergroup(@PathVariable("id") long id){
        UserGroup userGroup = userGroupRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User Group not exist with id : " + id));
        userGroupRepository.delete(userGroup);
        userGroupService.deleteusergroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
