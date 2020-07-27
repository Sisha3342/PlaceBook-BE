package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.PlaceHistoryDto;
import com.exadel.placebook.model.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class PlaceHistoryConverter {
    public PlaceHistoryDto convert(Booking booking) {
       PlaceHistoryDto phd = new PlaceHistoryDto();
        phd.setId(booking.getId());
        phd.setUserName(booking.getUser().getName());
        phd.setUserSurname(booking.getUser().getSurname());
        phd.setTimeStart(booking.getTimeStart());
        phd.setTimeEnd(booking.getTimeEnd());
        return phd;
    }

}
