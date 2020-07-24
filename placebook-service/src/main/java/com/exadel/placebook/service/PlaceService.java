package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.PlaceDto;

public interface PlaceService {
    PlaceDto getPlaceByUserNow(Long userId);
}
