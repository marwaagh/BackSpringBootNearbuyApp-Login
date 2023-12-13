package com.example.BackSpringBoot.appuser;



import com.example.BackSpringBoot.model.AccessPerModule;
import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.UserGroup;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "APPUSER")
public class AppUser implements UserDetails {


   @SequenceGenerator(
            name = "appuser_sequence",
            sequenceName = "appuser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appuser_sequence"
    )
    private Long id;
    @CreationTimestamp
    private Timestamp ts;
    //private String appOwner;
    private String username;
    private String firstName;
    private String lastName;
    //private String number;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = true;

    @ManyToOne
    private UserGroup userGroup;

    @ManyToOne
    private ClientSite pkClientSite;

    @ManyToMany
    @JoinTable(
            name = "appuser_accesspermodule",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "accesspermodule_id")
    )
    private Set<AccessPerModule> accessPerModules;

    public AppUser(String username,
                   String firstName,
                   String lastName,
                   // String email,
                   //String number,
                   String password,
                   AppUserRole appUserRole,
                   ClientSite pkClientSite,
                   UserGroup userGroup,
                   Set<AccessPerModule> accessPerModules) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.email = email;
        //this.number = number;
        this.password = password;
        this.appUserRole = appUserRole;
        this.pkClientSite = pkClientSite;
        this.userGroup = userGroup;
        this.accessPerModules = accessPerModules;
    }

    @JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /*public String getNumber() {
        return number;
    }*/


    /*public String getEmail() {
        return email;
    }*/

    /*public void setEmail(String email) {
        this.email = email;
    }*/

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
