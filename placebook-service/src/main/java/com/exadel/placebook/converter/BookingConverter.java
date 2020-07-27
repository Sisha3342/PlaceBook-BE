package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {

    @Autowired
    private AddressConverter addressConverter;

    public BookingDto convert(Booking booking) {
        com.exadel.placebook.model.dto.BookingDto bookingDto = new com.exadel.placebook.model.dto.BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setPlaceNumber(booking.getPlace().getPlaceNumber());
        bookingDto.setUserName(booking.getUser().getName());
        bookingDto.setUserSurname(booking.getUser().getSurname());
        bookingDto.setEmail(booking.getUser().getEmail());
        bookingDto.setAddress(addressConverter.convert(booking.getPlace().getFloor().getOffice().getAddress()));
        bookingDto.setTimeStart(booking.getTimeStart());
        bookingDto.setTimeEnd(booking.getTimeEnd());
        bookingDto.setStatus(booking.getStatus());
        return bookingDto;
    }

}