package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}