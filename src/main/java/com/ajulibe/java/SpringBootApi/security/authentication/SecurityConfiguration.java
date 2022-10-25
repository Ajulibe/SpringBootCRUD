package com.ajulibe.java.SpringBootApi.security.authentication;

import com.ajulibe.java.SpringBootApi.security.authentication.helpers.AuthSuccessHandler;
import com.ajulibe.java.SpringBootApi.security.authentication.filters.JsonObjectAuthenticationFilter;
import com.ajulibe.java.SpringBootApi.security.authentication.filters.JwtAuthorizationFilter;
import com.ajulibe.java.SpringBootApi.service.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/*
 * This is the main security configuration. To get this to work come sub-classes
 * were meade in the previous files
 * */

//@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Value("${ajulibe.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ajulibe.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthSuccessHandler authSuccessHandler;
    private final JwtUserDetailsService jwtUserDetailsService;


    public SecurityConfiguration(AuthSuccessHandler authSuccessHandler, JwtUserDetailsService jwtUserDetailsService) {
        this.authSuccessHandler = authSuccessHandler;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests((auth) -> {
                    try {
                        auth
                                .antMatchers("/user").hasRole("USER")
                                .antMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().permitAll()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                //add filters to validate the token
                                .addFilter(authenticationFilter())
                                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUserDetailsService))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


}
