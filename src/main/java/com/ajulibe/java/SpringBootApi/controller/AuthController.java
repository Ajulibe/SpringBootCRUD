package com.ajulibe.java.SpringBootApi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping("/not-logged")
    public String userEndpoint() {
        return "Hello user!, You are not logged in";
    }

    @RequestMapping("/login")
    public String adminEndpoint() {
        return "login";
    }
}
