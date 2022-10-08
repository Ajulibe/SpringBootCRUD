package com.ajulibe.java.SpringBootApi.dao;

import com.ajulibe.java.SpringBootApi.entity.Members;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class MembersDAOHibImpl implements MembersDAO {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public MembersDAOHibImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Members> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Members> theQuery =
                currentSession.createQuery("from Members", Members.class);

        // execute query and get result list
        List<Members> members = theQuery.getResultList();

        // return the results
        return members;
    }

    @Override
    public Members findById(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Members theMember =
                currentSession.get(Members.class, theId);

        // return the employee
        return theMember;
    }

    @Override
    public void save(Members theMember) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(theMember);

    }

    @Override
    public void deleteById(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Members where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();

    }
}
