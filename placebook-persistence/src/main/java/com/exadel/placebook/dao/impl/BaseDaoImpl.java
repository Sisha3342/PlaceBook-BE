package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BaseDao;
import com.exadel.placebook.model.entity.BaseEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public BaseDaoImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public T save(T object) {
        if (object != null) {
            Long id = (Long) getSession().save(object);
            object.setId(id);
        }
        return object;
    }

    @Override
    public T find(Long id) {
        return id == null ? null : getSession().get(entityClass, id);
    }


    @Override
    public boolean delete(Long id) {
        T object = find(id);
        if (object != null) {
            getSession().delete(object);
            return true;
        }
        return false;
    }


    @Override
    public T update(T object) {
        getSession().merge(object);
        return object;
    }
}
