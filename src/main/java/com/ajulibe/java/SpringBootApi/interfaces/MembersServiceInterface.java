package com.ajulibe.java.SpringBootApi.interfaces;

import com.ajulibe.java.SpringBootApi.entity.MembersEntity;

import java.util.List;
import java.util.Optional;

public interface MembersServiceInterface {

    public List<MembersEntity> findAll();

    public Optional<MembersEntity> findById(int theId);

    public void save(MembersEntity theMember);

    public void deleteById(int theId);
}
