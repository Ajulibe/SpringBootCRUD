package com.ajulibe.java.SpringBootApi.controller;


import com.ajulibe.java.SpringBootApi.config.permissions.ERole;
import com.ajulibe.java.SpringBootApi.dto.request.RegisterRequestDto;
import com.ajulibe.java.SpringBootApi.dto.response.MessageResponseDto;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.config.SecurityBeans;
import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import com.ajulibe.java.SpringBootApi.service.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@Controller
public class AuthenticationController {

    @Autowired
    private JwtUserRepo userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginCredentials getUserDetails(@RequestBody LoginCredentials loginPayload) {
        System.out.println(loginPayload + "logg in credentials");
        //generate the token
        //get the user details
        //get the claims on the user
        //get the user token
        return loginPayload;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        System.out.println(registerRequestDto + "at register request");

//        //Check if the username already exists
        if (userRepository.findJwtUserByUsername(registerRequestDto.username()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto("Error: Username is already in use!"));
        }
        //Check if the user Email already exists
        if (userRepository.findJwtUserByEmail(registerRequestDto.email()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto("Error: Username is already taken!"));
        }

        //pass the userRepository here
        UserService userService = new UserService(userRepository);
        userService.create(registerRequestDto);


//        //encode the password
//        SecurityBeans securityBeans = new SecurityBeans();
//        String encodedPassword = securityBeans.passwordEncoder().encode(registerRequestDto.password());

//
//        //Create and ascertain roles
//        Set<String> incomingRequestRoles = registerRequestDto.role();
//        Set<ERole> role = new HashSet<>();
//
//        //check if there are roles in the request, if not it's a user
//        if (incomingRequestRoles == null) {
//            role.add(ERole.role_user);
//        } else {
//            incomingRequestRoles.forEach(r -> {
//                switch (r) {
//                    case "role_admin" -> role.add(ERole.role_admin);
//                    case "role_moderator" -> role.add(ERole.role_moderator);
//                    default -> role.add(ERole.role_user);
//                }
//            });
//        }


//        //save the user
//        UserEntity user = new UserEntity();
//        user.setEmail(registerRequestDto.email());
//        user.setUsername(registerRequestDto.username());
//        user.setPassword(encodedPassword);
//        user.setRole(3);
//        user.setUuid(UUID.randomUUID().toString());
//        user.setEnabled(true);
//        user.setJoindate(new Date());
//
//        System.out.println(user + "user at the register endpoint");
//        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
