package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BaseDao;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
