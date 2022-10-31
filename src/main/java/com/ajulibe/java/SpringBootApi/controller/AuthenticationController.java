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
@RequiredArgsConstructor
public class AuthenticationController {

    private JwtUserRepo userRepository;

    private JwtUtils jwtUtils;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<LoginResponseDTO> getUserDetails(@RequestBody LoginCredentialsDTO loginPayload) {
        System.out.println(loginPayload + "logg in credentials----------------------------------------------------");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jwt = jwtUtils.generateToken(user);

        GrantedAuthority userRole = null;
        for (GrantedAuthority role : user.getAuthorities()) {
            userRole = role;
        }
        return ResponseEntity.ok(new LoginResponseDTO(jwt,
                user.getUsername(),
                loginPayload.email(),
                user.isEnabled(),
                userRole
        ));
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
