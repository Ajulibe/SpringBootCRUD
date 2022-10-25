package com.ajulibe.java.SpringBootApi.controller;


import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginCredentials login(@RequestBody LoginCredentials loginPayload)
            throws Exception {
        System.out.println(loginPayload);
        return loginPayload;
    }


}
