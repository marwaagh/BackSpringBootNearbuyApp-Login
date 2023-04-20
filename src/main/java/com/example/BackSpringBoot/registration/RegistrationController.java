package com.example.BackSpringBoot.registration;


import com.example.BackSpringBoot.registration.jwt.JwtUtils;
import com.example.BackSpringBoot.appuser.AppUser;
import com.example.BackSpringBoot.appuser.AppUserRepository;
import com.example.BackSpringBoot.appuser.AppUserRole;
import com.example.BackSpringBoot.appuser.AppUserService;
import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.registration.token.ConfirmationToken;
import com.example.BackSpringBoot.registration.token.ConfirmationTokenRepository;
import com.example.BackSpringBoot.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("**")
@RestController
@RequestMapping(path = "api/v1/ipersyst")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RegistrationService registrationService;
    @Autowired
    private AppUserRepository appUserRepository;

    private AppUserService appUserService;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserDetailsService;
    private final JwtUtils jwtUtils;


    //register user (add user)
    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        //return registrationService.register(request);
        registrationService.register(request);
    }

    @GetMapping("appUser/find/{firstname}")
    public ResponseEntity<AppUser> getUserByName(@PathVariable("firstname") String name) {
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(name);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


    // get all users
    @GetMapping(path = "appUser")
    public List<AppUser> getAllAppUser() {
        return appUserRepository.findAll();
    }

    // get user by id
    @GetMapping("appUser/{id}")
    public ResponseEntity<AppUser> getAppUserById(@PathVariable long id) {
        AppUser appuser = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" user not exist with id: " + id));
        return ResponseEntity.ok(appuser);
    }
/*get user by user group
    @GetMapping("appUser/ref/{ref}")
    public ResponseEntity<AppUser[]> getAppUserByReference(@PathVariable AppUserRole ref) {
        List<AppUser> appUserList = List.of(appUserRepository.findAllByAppUserRole(ref).orElseThrow(() -> new ResourceNotFoundException(" user group not exist")));
        AppUser[] appuser = appUserList.toArray(new AppUser[appUserList.size()]);
        return ResponseEntity.ok(appuser);
    }*/

    // update user
    @PutMapping("{id}")
    public String updateAppUser(@PathVariable long id, @RequestBody AppUser appUserDetails) {
        AppUser updateAppUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" user not exist with id: " + id));


        updateAppUser.setUsername(appUserDetails.getUsername());
        updateAppUser.setFirstName(appUserDetails.getFirstName());
        updateAppUser.setLastName(appUserDetails.getLastName());
        updateAppUser.setEmail(appUserDetails.getEmail());
        updateAppUser.setNumber(appUserDetails.getNumber());
        updateAppUser.setPassword(bCryptPasswordEncoder.encode(appUserDetails.getPassword()));
       /* updateAppUser.setAppUserRole(AppUserRole.USER);
        updateAppUser.setEnabled(appUserDetails.getEnabled());
        updateAppUser.setLocked(appUserDetails.getLocked());*/

        appUserRepository.save(updateAppUser);

        return "user updated !!!!";
    }

    // delete user
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAppUser(@PathVariable long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));

        // Delete related confirmation tokens

        List<ConfirmationToken> tokens = confirmationTokenRepository.findByAppUser(appUser);
        confirmationTokenRepository.deleteAll(tokens);

        // Delete the user record
        appUserRepository.delete(appUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
/*
// login for all users
    @PostMapping(path="login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
                Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

        AppUser user = (AppUser) authenticate.getPrincipal();
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                jwtUtils.generateToken(user)
        ).body(user);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }*/

//login admin
    @PostMapping(path="login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            AppUser user = (AppUser) authenticate.getPrincipal();

            // Check if user has ADMIN role
            if (user.getAppUserRole().equals(AppUserRole.ADMIN.name())) {
                return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                        jwtUtils.generateToken(user)
                ).body(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
// reset password
     /*   @PutMapping("resetPassword")
        public ResponseEntity<?> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
            AppUser user = appUserRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new InvalidPasswordException("Invalid old password");
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            appUserRepository.save(user);

            return ResponseEntity.ok().build();
        }

    @PostMapping(path = "reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {

        // Find the user by email
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        // Generate a new confirmation token for resetting the password
        ConfirmationToken token = new ConfirmationToken(user);

        // Save the confirmation token in the database
        confirmationTokenRepository.save(token);

        // Send the email with the password reset link
        String resetPasswordLink = "https://example.com/reset-password?token=" + token.getToken();
        String message = "Please click the following link to reset your password: " + resetPasswordLink;
        emailService.sendEmail(request.getEmail(), "Reset Password", message);

        return ResponseEntity.ok("Password reset email sent successfully");
    }
*/
}
