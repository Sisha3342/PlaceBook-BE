package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.entity.Place;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao {

    @Override
    public List<Place> findPlacesByFloorId(Long floorId){
        Session session = getSession();
        return session.createQuery("select p from Place p where p.floor.id = :id", Place.class)
                .setParameter("id", floorId).list();
    }
}
