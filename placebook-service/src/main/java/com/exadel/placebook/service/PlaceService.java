package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.PlaceBlockResponse;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.dto.PlaceSearchDto;

import java.util.List;

public interface PlaceService {
    List<PlaceSearchDto> getPlaceByUserNow(Long userId);
    PlaceDto subscribeToPlace(Long placeId);
    void subscribeChecker();

    PlaceBlockResponse blockPlaceForUser(Long placeId, Long userId);
}
