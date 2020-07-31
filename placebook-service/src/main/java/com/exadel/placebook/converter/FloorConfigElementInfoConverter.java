package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.DashboardElementInfo;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.PlaceStatus;
import org.springframework.stereotype.Component;

@Component
public class FloorConfigElementInfoConverter {

    public Place convert(DashboardElementInfo elementInfo) {
        Place place = new Place();

        place.setPlaceStatus(elementInfo.isActive() ? PlaceStatus.ACTIVE : PlaceStatus.INACTIVE);
        place.setPlaceNumber(elementInfo.getNumber());

        return place;
    }
}
