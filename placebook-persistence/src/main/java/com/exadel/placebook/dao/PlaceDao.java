package com.exadel.placebook.dao;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;

import java.util.List;

public interface PlaceDao extends BaseDao<Place> {
    List<PlaceDto> getPlaceByUserNow(Long userId);
}

