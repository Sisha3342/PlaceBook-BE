package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.PlaceDto;

import java.util.List;

public interface PlaceService {
    List<PlaceDto> getPlaceByUserNow(Long userId);
}
