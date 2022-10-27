package com.ajulibe.java.SpringBootApi.security.authentication.filters;


import com.ajulibe.java.SpringBootApi.security.authentication.helpers.JwtUtils;
import com.ajulibe.java.SpringBootApi.service.jwt.JwtUserDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @order -- this executes First
 * This will search for authorization headers in the incoming request
 * essentially to validate the token present
 */

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtUserDetailsService jwtUserDetailsService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUserDetailsService jwtUserDetailsService) {
        super(authenticationManager);
        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken auth = getAuthentication(request);
        if (auth == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //if there isn't a token, or the token doesn't start with "Bearer" then the request
        //is not authorized
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            return null;
        }

        JwtUtils jwtUtils = new JwtUtils();
        String email = jwtUtils.getEmailFromToken(token);

        //check if there is an email associated with the token
        if (email == null) return null;
        //if the token is actually valid, then return the user with that email
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
