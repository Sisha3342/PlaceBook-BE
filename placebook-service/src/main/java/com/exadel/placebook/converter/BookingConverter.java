package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingConverter {
    public BookingDto convert(Booking b) {
        BookingDto bd = new BookingDto();
        bd.setId(b.getId());
        bd.setPlaceId(b.getPlaceId());
        bd.setUserId(b.getUserId());
        bd.setTimeStart(b.getTimeStart());
        bd.setTimeEnd(b.getTimeEnd());
        bd.setStatus(b.getStatus());
        return bd;
    }

    public Booking convert(BookingDto bd) {
        Booking b = new Booking();
        b.setId(bd.getId());
        b.setPlaceId(bd.getPlaceId());
        b.setUserId(bd.getUserId());
        b.setTimeStart(bd.getTimeStart());
        b.setTimeEnd(bd.getTimeEnd());
        b.setStatus((Status) bd.getStatus());
        return b;
    }

}
