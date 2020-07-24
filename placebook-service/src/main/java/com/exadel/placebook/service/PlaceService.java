package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.PlaceSearchDto;

import java.util.List;

public interface PlaceService {
    List<PlaceSearchDto> getPlaceByUserNow(Long userId);
}
