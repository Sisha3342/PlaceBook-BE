package com.exadel.placebook.service.impl;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.SecurityValidationService;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityValidationServiceImpl implements SecurityValidationService {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    public boolean isUserCanAddBooking(Long id) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(id);

        return (currentUserStatus.getRole().equals(Role.HR) &&
                userDto.getHrId().equals(currentUserStatus.getId())) ||
                (currentUserStatus.getRole().equals(Role.USER) &&
                        currentUserStatus.getId().equals(id));
    }
}
