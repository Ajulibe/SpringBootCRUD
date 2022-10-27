package com.ajulibe.java.SpringBootApi.service.jwt;


import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserService {

    private final JwtUserRepo jwtUserRepo;

    public UserEntity save(UserEntity user) {
        return jwtUserRepo.save(user);
    }

    public UserEntity getJwtUserByEmail(String email) {
        System.out.println(email + "email in User service");
        return jwtUserRepo.findJwtUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email!"));
    }

    public UserEntity getJwtUserByUsername(String username) {
        return jwtUserRepo.findJwtUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found by username!"));
    }

}
