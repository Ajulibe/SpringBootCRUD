package com.ajulibe.java.SpringBootApi.controller;


import com.ajulibe.java.SpringBootApi.security.domain.LoginCredentials;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginCredentials login(@RequestBody LoginCredentials loginPayload)
            throws Exception {
        System.out.println("------------- 🔥 Came into this route----------------");
        System.out.println(loginPayload);
        return loginPayload;
    }


}
