package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.entity.Office;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OfficeDaoImpl extends BaseDaoImpl<Office> implements OfficeDao {


    @Override
    public List<Office> findOfficesByCityAndCountry(String city, String country) {
        Session session = getSession();
        return session
                .createQuery("select o from Office o join o.address a where a.city = :city and a.country = :country", Office.class)
                .setParameter("city", city)
                .setParameter("country", country)
                .list();
    }



}
