package com.ajulibe.java.SpringBootApi.dto.response;

import org.springframework.security.core.GrantedAuthority;


public record LoginResponseDTO (
         String token,
         String username,
         String email,
         Boolean Enabled,
         GrantedAuthority role
){

}

