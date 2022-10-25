package com.ajulibe.java.SpringBootApi.security.authentication.filters;

import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * An authentication filter is a middleware that acts on requests before they reach your controller
 * */

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //converts a POJO (plain old java object) to a JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            //read the body of the incoming HTTP request
            BufferedReader reader = request.getReader();
            //initiate a string builder class to get all request params
            StringBuilder sb = new StringBuilder();
            String line;
            //read all params and make into a string
            while ((line = reader.readLine()) != null) {
                System.out.println("JsonObjectAuthenticationFilter" + line.toString());
                sb.append(line);
            }

            //converts the stringified JSON request to a java Object of type LoginCredentials
            //returns a POJO with login details gotten from the http body
            LoginCredentials authRequest = objectMapper.readValue(sb.toString(), LoginCredentials.class);

            //returns a token using the login details
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword()
            );
            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
