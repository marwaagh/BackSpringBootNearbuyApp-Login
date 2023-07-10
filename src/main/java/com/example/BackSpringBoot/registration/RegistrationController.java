package com.example.BackSpringBoot.registration;


import com.example.BackSpringBoot.registration.jwt.JwtUtils;
import com.example.BackSpringBoot.appuser.AppUser;
import com.example.BackSpringBoot.appuser.AppUserRepository;
import com.example.BackSpringBoot.appuser.AppUserRole;
import com.example.BackSpringBoot.appuser.AppUserService;
import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.registration.token.ConfirmationToken;
import com.example.BackSpringBoot.registration.token.ConfirmationTokenRepository;
import com.example.BackSpringBoot.service.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @Autowired
    private ReportService reportService;

    private final RegistrationService registrationService;
    @Autowired
    private AppUserRepository appUserRepository;

    private AppUserService appUserService;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    //register user (add user)
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
        //registrationService.register(request);
    }

    @GetMapping("appUser/find/{username}")
    public ResponseEntity<AppUser> getUserByName(@PathVariable String username) {
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(username);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


    // get all users
    @GetMapping("appUser")
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
        //updateAppUser.setEmail(appUserDetails.getEmail());
        //updateAppUser.setNumber(appUserDetails.getNumber());
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
            //if (user.getAppUserRole().equals(AppUserRole.USER.name())) {
                return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                        jwtUtils.generateToken(user)
                ).body(user);
            //} else {
           //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           // }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    //Reports
    @GetMapping("/report/ficheuser/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws IOException, JRException {
        return reportService.exportRapportFicheUser(id);
    }

    @GetMapping("/report/listeusers")
    public ResponseEntity<byte[]> generateReportListeCltSite() throws IOException, JRException {
        return reportService.exportRapportListeUser();
    }


}
