package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao {
    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public List<PlaceSearchDto> getPlaceByUserNow(Long userId) {
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
                .setResultTransformer(Transformers.aliasToBean(PlaceSearchDto.class))
                .setParameter("userId", userId)
                .setParameter("activeStatus", Status.ACTIVE).list();
    }

    @Override
    public List<Place> findPlacesByFloorId(Long floorId) {
        Session session = getSession();
        return session.createQuery("select p from Place p where p.floor.id = :id", Place.class)
                .setParameter("id", floorId).list();
    }

    @Override
    public Place findByBookingRequest(BookingRequest bookingRequest) {
        return getSession().createQuery("select p from Place p join p.floor f where " +
                "f.office.id = :officeId and " +
                "f.floorNumber = :floorNumber and " +
                "p.placeNumber = :placeNumber and " +
                "(select count (b) from Booking b " +
                "where b.place.id = p.id and " +
                "b.timeEnd > :timeStart and b.timeStart < :timeEnd) = 0", Place.class)
                .setParameter("timeStart", bookingRequest.getTimeStart())
                .setParameter("timeEnd", bookingRequest.getTimeEnd())
                .setParameter("floorNumber", bookingRequest.getFloorNumber())
                .setParameter("placeNumber", bookingRequest.getPlaceNumber())
                .setParameter("officeId", bookingRequest.getOfficeId())
                .getSingleResult();
    }

    @Override
    public List<Place> getFreePlacesByFloorIdAndTimeRange(Long floorId, LocalDateTime start, LocalDateTime end) {
        Session session = getSession();
        return session.createQuery("select p from Place p " +
                        "where p.floor.id = :id and " +
                        "(select count (b) from Booking b " +
                        "where b.place.id = p.id and " +
                        "b.timeEnd > :timeStart and b.timeStart < :timeEnd) = 0"
                , Place.class)
                .setParameter("id", floorId)
                .setParameter("timeStart", start)
                .setParameter("timeEnd", end)
                .list();
    }
}