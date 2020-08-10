package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.MarkDao;
import com.exadel.placebook.model.entity.PlaceRate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MarkDaoImpl extends BaseDaoImpl<PlaceRate> implements MarkDao {
    @Override
    public Optional<PlaceRate> getMarksByBookingId(Long id) {
        Session session = getSession();
        return session.createQuery("from PlaceRate pr " +
                "left join fetch pr.booking b " +
                "left join fetch b.place p " +
                "left join fetch b.user u " +
                "left join fetch p.floor f " +
                "left join fetch f.office off " +
                "left join fetch off.address a " +
                "where b" +
                ".id = :bookingId", PlaceRate.class)
                .setParameter("bookingId", id)
                .uniqueResultOptional();
    }

    @Override
    public PlaceRate checkMarksByBookingId(Long id) {
        Session session = getSession();
        return session.createQuery("from PlaceRate pr " +
                "left join fetch pr.booking b " +
                "left join fetch b.place p " +
                "left join fetch b.user u " +
                "left join fetch p.floor f " +
                "left join fetch f.office off " +
                "left join fetch off.address a " +
                "where b" +
                ".id = :bookingId", PlaceRate.class)
                .setParameter("bookingId", id)
                .uniqueResult();
    }

}