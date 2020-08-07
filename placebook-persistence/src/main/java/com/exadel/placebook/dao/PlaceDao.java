package com.exadel.placebook.dao;

import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.model.entity.Place;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PlaceDao extends BaseDao<Place> {
    List<PlaceSearchDto> getPlaceByUserNow(Long userId);

    List<Place> findPlacesByFloorId(Long floorId);

    List<Place> getFreePlacesByFloorIdAndTimeRange(Long floorId, LocalDateTime start, LocalDateTime end);

    Map<Place, Boolean> getPlacesWithOccupation(Long floorId, LocalDateTime timeStart, LocalDateTime timeEnd);
}

