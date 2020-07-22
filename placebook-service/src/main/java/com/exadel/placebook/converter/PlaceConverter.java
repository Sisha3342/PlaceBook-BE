package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.PlaceStatus;
import org.springframework.stereotype.Component;

@Component
public class PlaceConverter {
    public PlaceDto convert(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setIsActive(place.getPlaceStatus().equals(PlaceStatus.ACTIVE));
        placeDto.setPlaceId(place.getId());
        place.setPlaceNumber(place.getPlaceNumber());

        return placeDto;
    }
}
