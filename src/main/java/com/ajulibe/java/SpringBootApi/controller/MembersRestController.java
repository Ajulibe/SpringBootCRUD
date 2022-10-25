package com.ajulibe.java.SpringBootApi.controller;

import com.ajulibe.java.SpringBootApi.entity.MembersEntity;
import com.ajulibe.java.SpringBootApi.interfaces.MembersServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class MembersRestController {
    private MembersServiceInterface membersService;

    @GetMapping("/members")
    public List<MembersEntity> findAll() {
        return membersService.findAll();
    }
}
