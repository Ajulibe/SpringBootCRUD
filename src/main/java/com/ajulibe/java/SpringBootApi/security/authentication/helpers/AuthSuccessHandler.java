package com.ajulibe.java.SpringBootApi.security.authentication.helpers;

import com.ajulibe.java.SpringBootApi.dto.CurrentUserDTO;
import com.ajulibe.java.SpringBootApi.dto.response.LoginResponseDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * called when the user has been authenticated
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //get the current authenticated user
        CurrentUserDTO principal = (CurrentUserDTO) authentication.getPrincipal();
        //generate jwt
        String token = jwtUtils.generateToken(principal);

        //Set response parameters
        PrintWriter out = response.getWriter();
        String username = principal.getUsername();
        String email = principal.getEmail();
        Boolean isEnabled = principal.isEnabled();
        GrantedAuthority userRole = null;
        for (GrantedAuthority role : principal.getAuthorities()) {
            userRole = role;
        }

        LoginResponseDTO loginResponse = new LoginResponseDTO(token, username, email, isEnabled, userRole);
        String userDetailsJsonString = new Gson().toJson(loginResponse);
        out.write(userDetailsJsonString);

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "application/json");
        out.flush();
    }


}
