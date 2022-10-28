package com.ajulibe.java.SpringBootApi.controller;


import com.ajulibe.java.SpringBootApi.dto.LoginCredentialsDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class SecondaryController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginCredentialsDTO login(@RequestBody LoginCredentialsDTO loginPayload)
            throws Exception {
        System.out.println("------------- 🔥 Came into this route----------------");
        System.out.println(loginPayload);
        return loginPayload;
    }


}
