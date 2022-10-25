package com.ajulibe.java.SpringBootApi.interfaces;

import com.ajulibe.java.SpringBootApi.entity.MembersEntity;
import java.util.List;
import java.util.Optional;


public interface MembersInterface {

    List<MembersEntity> findAll();

    Optional<MembersEntity> findById(int theId);

    Object save(MembersEntity theMember);

    void deleteById(int theId);
}

