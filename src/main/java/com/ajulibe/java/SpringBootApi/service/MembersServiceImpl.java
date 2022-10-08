package com.ajulibe.java.SpringBootApi.service;

import com.ajulibe.java.SpringBootApi.dao.MembersDAO;
import com.ajulibe.java.SpringBootApi.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembersServiceImpl implements MembersService {

    private MembersDAO membersDAO;

    @Autowired
    public MembersServiceImpl(@Qualifier("membersDAOJpaImpl") MembersDAO theMembersDAO) {
        membersDAO = theMembersDAO;
    }

    @Override
    @Transactional
    public List<Members> findAll() {
        return membersDAO.findAll();
    }

    @Override
    @Transactional
    public Members findById(int theId) {
        return membersDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Members theMember) {
        membersDAO.save(theMember);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        membersDAO.deleteById(theId);
    }
}
