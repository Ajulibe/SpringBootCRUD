package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.interfaces.MembersInterface;
import com.ajulibe.java.SpringBootApi.entity.MembersEntity;
import com.ajulibe.java.SpringBootApi.interfaces.MembersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembersService implements MembersServiceInterface {

    private MembersInterface membersDAO;

    @Autowired
    public MembersService(MembersInterface theMembersDAO) {
        membersDAO = theMembersDAO;
    }

    @Override
    @Transactional
    public List<MembersEntity> findAll() {
        return membersDAO.findAll();
    }

    @Override
    @Transactional
    public Optional<MembersEntity> findById(int theId) {
        return membersDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(MembersEntity theMember) {
        membersDAO.save(theMember);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        membersDAO.deleteById(theId);
    }
}
