package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.dto.request.RegisterRequestDTO;
import com.ajulibe.java.SpringBootApi.dto.response.LoginResponseDTO;
import com.ajulibe.java.SpringBootApi.dto.response.MessageResponseDTO;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.dto.LoginCredentialsDTO;
import com.ajulibe.java.SpringBootApi.security.authentication.helpers.JwtUtils;
import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import com.ajulibe.java.SpringBootApi.service.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


@Controller
public class AuthenticationController {
    @Autowired
    private JwtUserRepo userRepository;


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String getUserDetails(@RequestBody LoginCredentialsDTO loginPayload) {
//        System.out.println(loginPayload + "logg in credentials");
//        //generation of the token happens through the filters in the SecurityConfiguration file
//        //SecurityContextHolder and all the required data is configured and placed for us to us
//
////        //get the user details
//////        Authentication authentication = authenticationManager.authenticate(
//////                new UsernamePasswordAuthenticationToken(loginPayload.email(), loginPayload.password()));
//////
//////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//////        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////        String jwt = jwtUtils.generateToken(user);
////
////        GrantedAuthority userRole = null;
////        for (GrantedAuthority role : user.getAuthorities()) {
////            userRole = role;
////        }
////        //get the claims on the user
////        //get the user token
////        return ResponseEntity.ok(new LoginResponseDTO(jwt,
////                user.getUsername(),
////                loginPayload.email(),
////                user.isEnabled(),
////                userRole
////        ));
//
//        return "done";
//    }

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
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDto) {
        //Check if the username already exists
        if (userRepository.findJwtUserByUsername(registerRequestDto.username()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Username is already in use!"));
        }
        //Check if the user Email already exists
        if (userRepository.findJwtUserByEmail(registerRequestDto.email()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Username is already taken!"));
        }
        //pass the userRepository here
        UserService userService = new UserService(userRepository);
        userService.create(registerRequestDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
