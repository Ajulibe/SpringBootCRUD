package com.ajulibe.java.SpringBootApi.dao;

import com.ajulibe.java.SpringBootApi.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MembersDAOJPAImpl implements MembersDAO {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public MembersDAOJPAImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<Members> findAll() {
        // create a query
        Query theQuery =
                entityManager.createQuery("from Members");

        // execute query and get result list
        List<Members> membersList = theQuery.getResultList();

        // return the results
        return membersList;
    }

    @Override
    @Transactional
    public Members findById(int theId) {
        // get employee
        Members theMember =
                entityManager.find(Members.class, theId);

        // return employee
        return theMember;
    }

    @Override
    @Transactional
    public void save(Members theMember) {

        // save or update the employee
        Members dbMembers = entityManager.merge(theMember);

        // update with id from db ... so we can get generated id for save/insert
        theMember.setMemid(dbMembers.getMemid());
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
