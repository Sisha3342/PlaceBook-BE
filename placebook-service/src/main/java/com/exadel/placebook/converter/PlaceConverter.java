package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.PlaceStatus;
import org.springframework.stereotype.Component;

@Component
public class PlaceConverter {
    public PlaceDto convert(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setActive(place.getPlaceStatus().equals(PlaceStatus.ACTIVE));
        placeDto.setPlaceId(place.getId());
        placeDto.setPlaceNumber(place.getPlaceNumber());

        return placeDto;
    }
}