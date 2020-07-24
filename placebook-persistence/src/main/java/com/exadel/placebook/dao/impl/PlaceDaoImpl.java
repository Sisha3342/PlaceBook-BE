package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.time.LocalDateTime;

@Repository
public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao {
    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public List<PlaceDto> getPlaceByUserNow(Long userId) {
        Session session = getSession();
        return session.createQuery("select f.floorNumber as floorNumber, " +
                "p.placeNumber as placeNumber " +
                "from User u " +
                "join u.bookings b " +
                "join b.place p " +
                "join p.floor f " +
                "where u.id = :userId " +
                "and b.status = :activeStatus " +
                "and b.timeStart < current_timestamp() " +
                "and b.timeEnd > current_timestamp()")
                .setResultTransformer(Transformers.aliasToBean(PlaceDto.class))
                .setParameter("userId", userId)
                .setParameter("activeStatus", Status.ACTIVE).list();
    }

    @Override
    public List<Place> findPlacesByFloorId(Long floorId){
        Session session = getSession();
        return session.createQuery("select p from Place p where p.floor.id = :id", Place.class)
                .setParameter("id", floorId).list();
    }

    @Override
    public long countBookingsByPlaceIdAndTime(Long placeId, LocalDateTime start, LocalDateTime end, Long userId) {
        Session session = getSession();
        return session.createQuery("select count (b) from Booking b " +
                "where b.place.id = :id and " +
                "((:timeStart between b.timeStart and b.timeEnd) or " +
                "(:timeEnd between b.timeStart and b.timeEnd)) and " +
                "b.user.id <> :userId", Long.class)
                .setParameter("id", placeId)
                .setParameter("timeStart", start)
                .setParameter("timeEnd", end)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
