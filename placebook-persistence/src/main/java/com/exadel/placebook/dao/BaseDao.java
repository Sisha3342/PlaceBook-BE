package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.BaseEntity;
import org.hibernate.Session;

public interface BaseDao<T extends BaseEntity> {

    Session getSession();

    T save(T object);

    void saveOrUpdate(T object);

    T find(Long id);

    T load(Long id);

    boolean delete(Long id);

    T update(T object);

}
