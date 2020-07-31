package com.exadel.placebook.dao.impl;


import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Office;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OfficeDaoImpl extends BaseDaoImpl<Office> implements OfficeDao {

    @Override
    public List<Office> findOfficesByCityAndCountry(String city, String country) {
        Session session = getSession();
        return session
                .createQuery("select o from Office o join o.address a where " +
                        "(:city is null or a.city = :city) and " +
                        "(:country is null or a.country = :country)", Office.class)
                .setParameter("city", city)
                .setParameter("country", country)
                .list();
    }

    @Override
    public List<Floor> findFloorsByOfficeId(Long officeId) {
        Session session = getSession();
        Query<Floor> query = session
                .createQuery("select f from Floor f join f.office o where o.id = :id and f.deleted = false ", Floor.class)
                .setParameter("id", officeId);
        return query.list();
    }
}