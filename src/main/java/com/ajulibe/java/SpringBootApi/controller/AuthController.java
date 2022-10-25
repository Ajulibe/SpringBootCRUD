package com.ajulibe.java.SpringBootApi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @RequestMapping("/user")
    public String userEndpoint() {
        return "Hello user!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello admin!";
    }
}
