package com.exadel.placebook.converter;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.AddBookingDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddBookingConverter {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private UserService userService;

    public Booking convert(AddBookingDto dto) {
        Booking booking = new Booking();
        booking.setPlace(placeDao.find(dto.getPlaceId()));
        booking.setStatus(Status.ACTIVE);
        booking.setTimeStart(dto.getTimeStart());
        booking.setTimeEnd(dto.getTimeEnd());
        booking.setUser(userDao.find(userService.getUserStatus().getId()));

        return booking;
    }
}
