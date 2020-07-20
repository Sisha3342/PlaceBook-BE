package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.entity.*;
import org.springframework.stereotype.Component;

@Component
public class BookingInfoConverter {
    public BookingInfoDto convert(Booking booking) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        bookingInfoDto.setId(booking.getId());
        bookingInfoDto.setUserName(booking.getUser().getName());
        bookingInfoDto.setUserSurname(booking.getUser().getSurname());
        bookingInfoDto.setPlaceInfo(booking.getPlace().getPlaceNumber());
        bookingInfoDto.setAddress(booking.getPlace().getFloor().getOffice().getAddress().getAddress());
        bookingInfoDto.setTimeStart(booking.getTimeStart());
        bookingInfoDto.setTimeEnd(booking.getTimeEnd());
        bookingInfoDto.setStatus(booking.getStatus());
        return bookingInfoDto;
    }
}