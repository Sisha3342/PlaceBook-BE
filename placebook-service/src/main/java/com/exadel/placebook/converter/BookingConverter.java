package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {
    public BookingDto convert(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        //bookingDto.setPlaceId(booking.getPlace().getId());
        bookingDto.setUser(booking.getUser());
        bookingDto.setTimeStart(booking.getTimeStart());
        bookingDto.setTimeEnd(booking.getTimeEnd());
        bookingDto.setStatus(booking.getStatus());
        return bookingDto;
    }
}
