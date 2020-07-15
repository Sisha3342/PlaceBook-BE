package com.exadel.placebook.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.exadel.placebook.model.entity.Person;
import com.exadel.placebook.dao.PersonDao;

/**
 * TODO
 *
 * @author Anton Harakh
 * @since 7/8/2019
 */
@Repository
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {
    @Override
    public Person findById(Long id) {
        Session session = getSession();
        return session
                .createQuery("from Person p where p.id = :id", Person.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Person findByEmail(String email) {
        Session session = getSession();
        return session
                .createQuery("from Person p where p.email = :email", Person.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}
