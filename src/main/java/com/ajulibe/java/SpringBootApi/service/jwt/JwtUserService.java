package com.ajulibe.java.SpringBootApi.service.jwt;


import com.ajulibe.java.SpringBootApi.entity.JwtUserEntity;
import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserService {

    private final JwtUserRepo jwtUserRepo;

    public JwtUserEntity save(JwtUserEntity user) {
        return jwtUserRepo.save(user);
    }

    public Optional<JwtUserEntity> findJwtUserByEmail(String email) {
        return jwtUserRepo.findJwtUserByEmail(email);
    }

    public JwtUserEntity getJwtUserByEmail(String email) {
        return jwtUserRepo.findJwtUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email!"));
    }

    public JwtUserEntity getJwtUserByUsername(String username) {
        return jwtUserRepo.findJwtUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found by username!"));
    }

}
