package com.exadel.placebook.service.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.exception.SecurityValidationException;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.service.SecurityValidationService;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityValidationServiceImpl implements SecurityValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingDao bookingDao;

    @Override
    public void validateUserCanAddBooking(Long userId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(userId);

        if(currentUserStatus.getRole().equals(Role.HR) &&
                !userDto.getHrId().equals(currentUserStatus.getId()) ||
                !currentUserStatus.getId().equals(userId)) {
            throw new SecurityValidationException(String
                    .format("hr with id %d cant book place for user with id %d",
                    currentUserStatus.getId(),
                    userId));
        }

        if(currentUserStatus.getRole().in(Role.USER, Role.EDITOR) &&
                !currentUserStatus.getId().equals(userId)) {
            throw new SecurityValidationException(String
                    .format("user with id %d cant book place, using session of user %d",
                            userId,
                            currentUserStatus.getId()));
        }
    }

    @Override
    public void validateUserCanEditBooking(Long userId, Long bookingId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(userId);
        Booking booking = bookingDao.find(bookingId);

        if(currentUserStatus.getRole().equals(Role.HR) &&
                !(userDto.getHrId().equals(currentUserStatus.getId()) &&
                booking.getUser().getHrId().equals(currentUserStatus.getId())) ||
                !booking.getUser().getId().equals(currentUserStatus.getId())) {
            throw new SecurityValidationException(String
                    .format("hr with id %d cant edit booking %d for user with id %d",
                            currentUserStatus.getId(),
                            bookingId,
                            userId));
        }

        if(currentUserStatus.getRole().in(Role.USER, Role.EDITOR) &&
                !booking.getUser().getId().equals(currentUserStatus.getId())) {
            throw new SecurityValidationException(String.format("user with id %d cant edit booking with id %d", userId, bookingId));
        }
    }

    @Override
    public void validateUserCanDeleteBooking(Long bookingId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();

        validateUserCanEditBooking(currentUserStatus.getId(), bookingId);
    }

    @Override
    public void validateAdminCanChangeUserRole(Long userId) {
        if(!userService.getUserStatus().getId().equals(userId)) {
            throw new SecurityValidationException("admin cant change his own status");
        }
    }
}
