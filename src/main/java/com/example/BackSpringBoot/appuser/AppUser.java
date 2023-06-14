package com.example.BackSpringBoot.appuser;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
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

    private String username;
    private String firstName;
    private String lastName;
    //private String email;
    private String number;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = true;

    public AppUser(String username,
                   String firstName,
                   String lastName,
                  // String email,
                   String number,
                   String password,
                   AppUserRole appUserRole) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.email = email;
        this.number = number;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
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

    public String getNumber() {
        return number;
    }

    public String getAppUserRole() {
        return appUserRole.name();
    }

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
