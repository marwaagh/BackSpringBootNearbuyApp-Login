package com.example.BackSpringBoot.registration;


import com.example.BackSpringBoot.appuser.AppUserRole;
import com.example.BackSpringBoot.model.AccessPerModule;
import com.example.BackSpringBoot.model.ClientSite;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String username;
    private final String firstName;
    private final String lastName;
    //private final String email;
    //private final String number;
    private final String password;
    private final AppUserRole appUserRole;
    private final ClientSite pkClientSite;

    private Set<AccessPerModule> accessPerModules;
}
