package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.entity.Place;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao {

    @Override
    public List<Place> findPlacesByFloorId(Long floorId){
        Session session = getSession();
        return session.createQuery("select p from Place p where p.floor.id = :id", Place.class)
                .setParameter("id", floorId).list();
    }

    @Override
    public long countBookingsByPlaceIdAndTime(Long placeId, LocalDateTime start, LocalDateTime end) {
        Session session = getSession();
        return session.createQuery("select count (b) from Booking b " +
                "where b.place.id = :id and " +
                "(:timeStart between b.timeStart and b.timeEnd) or" +
                "(:timeEnd between b.timeStart and b.timeEnd)", Long.class)
                .setParameter("id", placeId)
                .setParameter("timeStart", start)
                .setParameter("timeEnd", end)
                .getSingleResult();
    }
}
