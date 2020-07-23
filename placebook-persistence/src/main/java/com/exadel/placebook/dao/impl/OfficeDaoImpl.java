package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.entity.Office;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OfficeDaoImpl extends BaseDaoImpl<Office> implements OfficeDao {


    @Override
    public List<Office> findAllOfficesByCity(String city) {
        Session session = getSession();
        Query<Office> query = session
                .createQuery("select o from Office o join o.address a where a.city = :city and o.deleted = false", Office.class)
                .setParameter("city", city);
        return query.list();
    }



}
