package com.exadel.placebook.dao;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;

import java.util.Optional;

public interface PlaceDao extends BaseDao<Place> {
    Optional<PlaceDto> getPlaceByUserNow(Long userId);
}

