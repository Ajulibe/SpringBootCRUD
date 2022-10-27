package com.ajulibe.java.SpringBootApi.service.jwt;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;
import com.ajulibe.java.SpringBootApi.dto.request.RegisterRequestDto;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.config.SecurityBeans;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService {
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

        UserEntity user = new UserEntity();
        //check if there are roles in the request, if not it's a user
        if (incomingRequestRoles == null) {
            int index = ERole.valueOf("role_user").ordinal();
            user.setRole(index + 1);
        } else {
            incomingRequestRoles.forEach(r -> {
                switch (r) {
                    case "role_admin" -> {
                        int index = ERole.valueOf("role_admin").ordinal();
                        user.setRole(index + 1);
                    }
                    case "role_moderator" -> {
                        int index = ERole.valueOf("role_moderator").ordinal();
                        user.setRole(index + 1);
                    }
                    default -> {
                        int index = ERole.valueOf("role_user").ordinal();
                        user.setRole(index + 1);
                    }
                }
            });
        }

        //save the user
        user.setEmail(registerRequestDto.email());
        user.setUsername(registerRequestDto.username());
        user.setPassword(encodedPassword);
        user.setUuid(UUID.randomUUID().toString());
        user.setEnabled(true);
        user.setJoindate(new Date());
        System.out.println(user + "user to be saved");
        userRepository.save(user);
    }

}
