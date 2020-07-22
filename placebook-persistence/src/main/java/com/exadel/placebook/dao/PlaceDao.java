package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Place;

import java.util.List;

public interface PlaceDao extends BaseDao<Place> {
    List<Place> findPlacesByFloorId(Long floorId);
}

