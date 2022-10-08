package com.ajulibe.java.SpringBootApi.dao;

import com.ajulibe.java.SpringBootApi.entity.Members;

import java.util.List;

public interface MembersDAO {

    public List<Members> findAll();

    public Members findById(int theId);

    public void save(Members theMember);

    public void deleteById(int theId);
}
