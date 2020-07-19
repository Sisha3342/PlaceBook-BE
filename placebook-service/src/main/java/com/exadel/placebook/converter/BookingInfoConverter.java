package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.entity.*;
import org.springframework.stereotype.Component;

@Component
public class BookingInfoConverter {
    public BookingInfoDto convert(Booking booking, User user, Place place, Country country, City city, Address address) {
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        bookingInfoDto.setId(booking.getId());
        bookingInfoDto.setUserName(user.getName());
        bookingInfoDto.setUserSurname(user.getSurname());
        bookingInfoDto.setPlaceInfo(place.getPlaceNumber());
        bookingInfoDto.setCountry(country.getCountry());
        bookingInfoDto.setCity(city.getCity());
        bookingInfoDto.setAddress(address.getAddress());
        bookingInfoDto.setTimeStart(booking.getTimeStart());
        bookingInfoDto.setTimeEnd(booking.getTimeEnd());
        bookingInfoDto.setStatus(booking.getStatus());
        return bookingInfoDto;
    }
}