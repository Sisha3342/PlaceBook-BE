package com.exadel.placebook.builder;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.AddBookingDto;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.MailMessageDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class MailMessageBuilder {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    public MailMessageDto convert(BookingDto bookingDto) {
        MailMessageDto messageDto = new MailMessageDto();
        messageDto.setPlaceNumber(bookingDto.getPlaceNumber());
        messageDto.setText("Your booking is CANCELLED!");
        messageDto.setUserName(bookingDto.getUserName());
        messageDto.setEmail(bookingDto.getEmail());
        messageDto.setCountry(bookingDto.getAddress().getCountry());
        messageDto.setCity(bookingDto.getAddress().getCity());
        messageDto.setOffice(bookingDto.getAddress().getAddress());
        messageDto.setTimeStart(bookingDto.getTimeStart());
        messageDto.setTimeEnd(bookingDto.getTimeEnd());
        return messageDto;
    }

    public MailMessageDto convert(AddBookingDto addBookingDto) {
        MailMessageDto messageDto = new MailMessageDto();
        Place place = placeDao.find(addBookingDto.getPlaceId());
        messageDto.setPlaceNumber(place.getPlaceNumber());
        messageDto.setText("You have just booked your place!");
        messageDto.setUserName(userDao.find(userService.getUserStatus().getId()).getName());
        messageDto.setEmail(userDao.find(userService.getUserStatus().getId()).getEmail());
        messageDto.setCountry(place.getFloor().getOffice().getAddress().getCountry());
        messageDto.setCity(place.getFloor().getOffice().getAddress().getCity());
        messageDto.setOffice(place.getFloor().getOffice().getAddress().getAddress());
        messageDto.setTimeStart(addBookingDto.getTimeStart());
        messageDto.setTimeEnd(addBookingDto.getTimeEnd());
        return messageDto;
    }
}
