package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.entity.Members;

import java.util.List;

public interface MembersService {

    public List<Members> findAll();

    public Members findById(int theId);

    public void save(Members theMember);

    public void deleteById(int theId);
}
