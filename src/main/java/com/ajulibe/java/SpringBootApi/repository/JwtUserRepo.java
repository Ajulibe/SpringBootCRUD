package com.ajulibe.java.SpringBootApi.repository;


import com.ajulibe.java.SpringBootApi.entity.JwtUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JwtUserRepo extends JpaRepository<JwtUserEntity, Long> {
    Optional<JwtUserEntity> findJwtUserByUsername(String username);
    Optional<JwtUserEntity> findJwtUserByEmail(String email);
}