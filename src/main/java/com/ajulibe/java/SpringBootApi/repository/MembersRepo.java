package com.ajulibe.java.SpringBootApi.repository;

import com.ajulibe.java.SpringBootApi.entity.MembersEntity;
import com.ajulibe.java.SpringBootApi.interfaces.MembersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class MembersRepo implements MembersInterface {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public MembersRepo(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<MembersEntity> findAll() {
        // create a query
        Query theQuery =
                entityManager.createQuery("from Members");

        // execute query and get result list
        List<MembersEntity> membersList = theQuery.getResultList();

        // return the results
        return membersList;
    }


    @Override
    @Transactional
    public Optional<MembersEntity> findById(int theId) {
        // get employee
        MembersEntity theMember =
                entityManager.find(MembersEntity.class, theId);

        // return employee
        return Optional.ofNullable(theMember);
    }


    @Override
    @Transactional
    public Object save(MembersEntity theMember) {

        // save or update the employee
        MembersEntity dbMembers = entityManager.merge(theMember);

        // update with id from db ... so we can get generated id for save/insert
        theMember.setMemid(dbMembers.getMemid());
        return null;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        // delete object with primary key
        Query theQuery = entityManager.createQuery(
                "delete from Members where id=:membersId");

        theQuery.setParameter("membersId", theId);

        theQuery.executeUpdate();
    }


}
