package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {
    public BookingDto convert(Booking booking) {
        com.exadel.placebook.model.dto.BookingDto bookingDto = new com.exadel.placebook.model.dto.BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setPlaceId(booking.getPlaceId());
        bookingDto.setUserId(booking.getUserId());
        bookingDto.setTimeStart(booking.getTimeStart());
        bookingDto.setTimeEnd(booking.getTimeEnd());
        bookingDto.setStatus(booking.getStatus());
        return bookingDto;
    }

    public Booking convert(BookingDto bookingDto) {

        Booking booking = new Booking();
        booking.setId(booking.getId());
        booking.setPlaceId(bookingDto.getPlaceId());
        booking.setUserId(bookingDto.getUserId());
        booking.setTimeStart(bookingDto.getTimeStart());
        booking.setTimeEnd(bookingDto.getTimeEnd());
        booking.setStatus(bookingDto.getStatus());
        return booking;
    }
}
