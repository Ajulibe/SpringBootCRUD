package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.entity.Members;
import com.ajulibe.java.SpringBootApi.service.MembersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class MembersRestController {
    private MembersService membersService;

//    @Autowired
//    public MembersRestController(MembersService theMembersService) {
//        membersService = theMembersService;
//    }

    //expose "/welcome"
    //use @Controller to return thymeleaf
    //@GetMapping("/welcome")
    //public String welcome() {
    //return "helloworld";
    //}

    // expose "/employees" and return list of employees
    @GetMapping("/members")
    public List<Members> findAll() {
        return membersService.findAll();
    }
}
