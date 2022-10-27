package com.ajulibe.java.SpringBootApi.dto;

import com.ajulibe.java.SpringBootApi.config.permissions.ERole;

import java.util.Date;
import java.util.Set;

public record UserView(
        Long id,
        String uuid,
        String username,
        String email,
        Set<ERole>role,
        Boolean enabled,
        Date joindate
) {

}
