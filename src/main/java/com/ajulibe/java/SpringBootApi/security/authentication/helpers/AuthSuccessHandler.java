package com.ajulibe.java.SpringBootApi.security.authentication.helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * called when the user has been authenticated
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //get the current authenticated user
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        //generate jwt
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.generateToken(principal);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "application/json");
        //add the token to the response body
        response.getWriter().write("{\"token\": \""+token+"\"}");
    }


}
