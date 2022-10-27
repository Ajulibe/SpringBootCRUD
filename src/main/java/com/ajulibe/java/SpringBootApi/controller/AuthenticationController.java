package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.dto.request.RegisterRequestDto;
import com.ajulibe.java.SpringBootApi.dto.response.MessageResponseDto;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import com.ajulibe.java.SpringBootApi.service.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


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
        //Check if the username already exists
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
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
