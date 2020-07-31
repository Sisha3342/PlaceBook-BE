package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingInfoConverter {

    @Autowired
    private AddressConverter addressConverter;

    public BookingInfoDto convert(Booking booking, MarkDto markDto) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        bookingInfoDto.setId(booking.getId());
        bookingInfoDto.setUserName(booking.getUser().getName());
        bookingInfoDto.setUserSurname(booking.getUser().getSurname());
        bookingInfoDto.setPlaceInfo(booking.getPlace().getPlaceNumber());
        bookingInfoDto.setUserId(booking.getUser().getId());
        bookingInfoDto.setPhotoUrl(booking.getUser().getPhotoUrl());
        bookingInfoDto.setAddress(addressConverter.convert(booking.getPlace().getFloor().getOffice().getAddress()));
        bookingInfoDto.setTimeStart(booking.getTimeStart());
        bookingInfoDto.setTimeEnd(booking.getTimeEnd());
        bookingInfoDto.setStatus(booking.getStatus());
        bookingInfoDto.setMarks(markDto);
        return bookingInfoDto;
    }
}