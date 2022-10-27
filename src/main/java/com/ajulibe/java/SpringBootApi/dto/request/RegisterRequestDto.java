package com.ajulibe.java.SpringBootApi.dto.request;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public record RegisterRequestDto(
        @NotBlank @Size String username,
        @NotBlank @Size String password,
        @NotBlank @Size(max = 50) @Email String email,

         Set<String>role

) {
}
