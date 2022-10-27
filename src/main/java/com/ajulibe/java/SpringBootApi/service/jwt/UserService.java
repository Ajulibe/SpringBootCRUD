package com.ajulibe.java.SpringBootApi.service.jwt;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;
import com.ajulibe.java.SpringBootApi.dto.request.CreateUser;
import com.ajulibe.java.SpringBootApi.dto.request.RegisterRequestDto;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.config.SecurityBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {
    CreateUser createUser;
    private final JwtUserRepo userRepository;

    public UserService(JwtUserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(RegisterRequestDto registerRequestDto) {
        SecurityBeans securityBeans = new SecurityBeans();
        String encodedPassword = securityBeans.passwordEncoder().encode(registerRequestDto.password());

        //Create and ascertain roles
        Set<String> incomingRequestRoles = registerRequestDto.role();
        Set<ERole> role = new HashSet<>();

        //check if there are roles in the request, if not it's a user
        if (incomingRequestRoles == null) {
            role.add(ERole.role_user);
        } else {
            incomingRequestRoles.forEach(r -> {
                switch (r) {
                    case "role_admin" -> role.add(ERole.role_admin);
                    case "role_moderator" -> role.add(ERole.role_moderator);
                    default -> role.add(ERole.role_user);
                }
            });
        }

        //save the user
        UserEntity user = new UserEntity();
        user.setEmail(registerRequestDto.email());
        user.setUsername(registerRequestDto.username());
        user.setPassword(encodedPassword);
        user.setRole(3);
        user.setUuid(UUID.randomUUID().toString());
        user.setEnabled(true);
        user.setJoindate(new Date());
        //UserEntity user = createUser.create(encodedPassword, role, registerRequestDto);
        System.out.println(user + "user to be saved");
        userRepository.save(user);
    }

}
