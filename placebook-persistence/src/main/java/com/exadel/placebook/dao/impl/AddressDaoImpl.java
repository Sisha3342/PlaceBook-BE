package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.AddressDao;
import com.exadel.placebook.model.entity.Address;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.Office;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao {

    @Override
    public List<String> findAllCountries() {
        Session session = getSession();
        Query<String> query = session
                .createQuery("select distinct a.country from Address a", String.class);
        return query.list();
    }

    @Override
    public List<String> findAllCitiesByCountry(String country) {
        Session session = getSession();
        Query<String> query = session
                .createQuery("select distinct a.city from Address a where a.country = :country", String.class)
                .setParameter("country", country);
        return query.list();
    }
}
