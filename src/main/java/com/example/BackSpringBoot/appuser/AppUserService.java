package com.example.BackSpringBoot.appuser;


import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.registration.jwt.JwtUtils;
import com.example.BackSpringBoot.registration.token.ConfirmationToken;
import com.example.BackSpringBoot.registration.token.ConfirmationTokenService;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";

    private final AppUserRepository appUserRepository;
    @Autowired
    private final ClientSiteRepository clientSiteRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }


    public String authenticateUser(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return jwtUtils.generateToken(userDetails);
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByUsername(appUser.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public List<AppUser> getAppUsersByClientSiteId(Long clientSiteId) {
        // Fetch the ClientSite object based on the provided clientSiteId
        // You should have a method in the ClientSiteRepository to fetch a ClientSite by ID.
        ClientSite clientSite = clientSiteRepository.findById(clientSiteId)
                .orElseThrow(() -> new RuntimeException("ClientSite not found with ID: " + clientSiteId));

        // Use the repository method to find all AppUsers associated with the ClientSite
        return appUserRepository.findAllByPkClientSite(clientSite);
    }

    /*public int enableAppUser (String email) {
        return appUserRepository.enableAppUser(email);
    }*/
}
