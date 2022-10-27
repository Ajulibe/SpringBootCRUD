package com.ajulibe.java.SpringBootApi.repository;


import com.ajulibe.java.SpringBootApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JwtUserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findJwtUserByUsername(String username);
    Optional<UserEntity> findJwtUserByEmail(String email);
}