package com.exadel.placebook.dao;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;
import java.time.LocalDateTime;
import java.util.List;

public interface PlaceDao extends BaseDao<Place> {
    List<PlaceDto> getPlaceByUserNow(Long userId);
    List<Place> findPlacesByFloorId(Long floorId);

    long countBookingsByPlaceIdAndTime(Long placeId, LocalDateTime start, LocalDateTime end, Long userId);

}

