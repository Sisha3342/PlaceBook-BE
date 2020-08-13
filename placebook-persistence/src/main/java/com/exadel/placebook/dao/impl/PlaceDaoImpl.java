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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Map<Place, Boolean> getPlacesWithOccupation(Long floorId, LocalDateTime timeStart, LocalDateTime timeEnd) {
        return getSession().createQuery("select p, count(b) from Place p left join p.bookings b " +
                "where p.floor.id = :floorId and " +
                "b.timeEnd > :timeStart and " +
                "b.timeStart < :timeEnd " +
                "group by p.id", Object[].class)
                .setParameter("floorId", floorId)
                .setParameter("timeStart", timeStart)
                .setParameter("timeEnd", timeEnd)
                .stream()
                .collect(Collectors.toMap(o -> (Place) o[0], o -> (Long)o[1] != 0));
    }
}
