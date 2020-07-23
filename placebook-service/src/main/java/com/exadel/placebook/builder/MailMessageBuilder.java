package com.exadel.placebook.builder;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.AddBookingDto;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.MailMessageDto;
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
        messageDto.setPlaceNumber((placeDao.find(addBookingDto.getPlaceId())).getPlaceNumber());
        messageDto.setUserName(userDao.find(userService.getUserStatus().getId()).getName());
        messageDto.setEmail(userDao.find(userService.getUserStatus().getId()).getEmail());
        messageDto.setCountry((placeDao.find(addBookingDto.getPlaceId())).getFloor().getOffice().getAddress().getCountry());
        messageDto.setCity((placeDao.find(addBookingDto.getPlaceId())).getFloor().getOffice().getAddress().getCity());
        messageDto.setOffice((placeDao.find(addBookingDto.getPlaceId())).getFloor().getOffice().getAddress().getAddress());
        messageDto.setTimeStart(addBookingDto.getTimeStart());
        messageDto.setTimeEnd(addBookingDto.getTimeEnd());
        return messageDto;
    }
}
