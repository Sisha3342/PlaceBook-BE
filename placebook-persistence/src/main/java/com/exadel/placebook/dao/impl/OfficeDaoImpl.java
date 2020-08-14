package com.exadel.placebook.dao.impl;


import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.sorting.OfficeSorting;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OfficeDaoImpl extends BaseDaoImpl<Office> implements OfficeDao {

    @Override
    public List<Office> findOfficesByCityAndCountry(String city, String country, OfficeSorting officeSorting) {
        Session session = getSession();
        String table = officeSorting.getOfficeSort().getSortingOption();
        String order = officeSorting.getOrder().getOrderOption();
        return session
                .createQuery("select o from Office o join o.address a where " +
                        "(:city is null or a.city = :city) and " +
                        "o.deleted=false and " +
                        "(:country is null or a.country = :country) " +
                        "order by " + table + " " + order, Office.class)
                .setParameter("city", city)
                .setParameter("country", country)
                .setMaxResults(officeSorting.getLimit())
                .setFirstResult(officeSorting.getOffset())
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

    @Override
    public Office findOfficeById(Long officeId) {
        return getSession().createQuery("select o from Office o where o.id = :officeId ", Office.class)
                .setParameter("officeId", officeId)
                .getSingleResult();
    }
}