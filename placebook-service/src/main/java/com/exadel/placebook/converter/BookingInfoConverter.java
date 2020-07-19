package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.entity.*;
import org.springframework.stereotype.Component;

@Component
public class BookingInfoConverter {
    public BookingInfoDto convert(Booking booking, Place place, Country country, City city, Address address) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        bookingInfoDto.setId(booking.getId());
        bookingInfoDto.setPlaceInfo(place.getPlaceNumber());
        bookingInfoDto.setTimeStart(booking.getTimeStart());
        bookingInfoDto.setTimeEnd(booking.getTimeEnd());
        bookingInfoDto.setStatus(booking.getStatus());
        return bookingInfoDto;
    }
}