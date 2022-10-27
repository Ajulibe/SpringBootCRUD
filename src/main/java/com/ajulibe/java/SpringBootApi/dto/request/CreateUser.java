package com.ajulibe.java.SpringBootApi.dto.request;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;
import com.ajulibe.java.SpringBootApi.entity.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record CreateUser(
        @NotBlank
        String password,
        @NotBlank
        Set<ERole> role,
        @NotBlank
        RegisterRequestDto registerRequestDto
) {

    public UserEntity CreateUser(
            @NotBlank
            String password,
            @NotBlank
            Set<ERole> role,
            @NotBlank
            RegisterRequestDto registerRequestDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registerRequestDto.username());
        user.setPassword(password);
//        user.setRole(role);
        user.setUuid(UUID.randomUUID().toString());
        user.setEnabled(true);
        user.setJoindate(new Date());
        return user;
    }

}