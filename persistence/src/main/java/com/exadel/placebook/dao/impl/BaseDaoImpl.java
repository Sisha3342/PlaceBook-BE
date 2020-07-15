package com.exadel.placebook.dao.impl;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.exadel.placebook.dao.BaseDao;

/**
 * TODO
 *
 * @author Anton Harakh
 * @since 7/9/2019
 */
public abstract class BaseDaoImpl implements BaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
