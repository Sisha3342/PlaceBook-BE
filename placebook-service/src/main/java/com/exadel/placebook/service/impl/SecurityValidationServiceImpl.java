package com.exadel.placebook.service.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.exception.SecurityValidationException;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.model.exception.EntityNotFoundException;
import com.exadel.placebook.service.SecurityValidationService;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityValidationServiceImpl implements SecurityValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingDao bookingDao;

    @Override
    public void validateUserCanAddBooking(Long userId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(userId);

        if (userDto == null) {
            throw new EntityNotFoundException(User.class, userId);
        }

        Long currentUserId = currentUserStatus.getId();

        switch (currentUserStatus.getRole()) {
            case HR:
                if (!userDto.getHrId().equals(currentUserId) &&
                        !currentUserId.equals(userId)) {
                    throw new SecurityValidationException(String
                            .format("hr with id %d cant book place for user with id %d",
                                    currentUserId,
                                    userId));
                }
                break;
            case USER:
            case EDITOR:
                if (!currentUserId.equals(userId)) {
                    throw new SecurityValidationException(String
                            .format("user with id %d cant book place, using session of user %d",
                                    userId,
                                    currentUserId));
                }
        }
    }

    @Override
    public void validateUserCanEditBooking(Long bookingId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        Booking booking = bookingDao.find(bookingId);

        if (booking == null) {
            throw new EntityNotFoundException(Booking.class, bookingId);
        }

        Long currentUserId = currentUserStatus.getId();
        User bookingOwner = booking.getUser();

        switch (currentUserStatus.getRole()) {
            case HR:
                if (!bookingOwner.getHrId().equals(currentUserId) &&
                        !bookingOwner.getId().equals(currentUserId)) {
                    throw new SecurityValidationException(String
                            .format("hr with id %d cant edit booking %d",
                                    currentUserStatus.getId(),
                                    bookingId));
                }
                break;
            case EDITOR:
            case USER:
                if (!bookingOwner.getId().equals(currentUserId)) {
                    throw new SecurityValidationException(String.format("user with id %d cant edit booking with id %d",
                            currentUserId,
                            bookingId));
                }
        }
    }

    @Override
    public void validateUserCanGetBookingInfo(Long bookingId) {
        UserStatusDto userStatus = userService.getUserStatus();
        Booking booking = bookingDao.find(bookingId);
        User bookingOwner = booking.getUser();

        switch (userStatus.getRole()) {
            case HR:
                if (!bookingOwner.getHrId().equals(userStatus.getId())) {
                    throw new SecurityValidationException(String.format("hr %s can't see booking of user %s",
                            userStatus.getName(),
                            bookingOwner.getName()));
                }
                break;
            case USER:
            case EDITOR:
            case ADMIN:
                if (!userStatus.getId().equals(bookingOwner.getId())) {
                    throw new SecurityValidationException(String.format("user %s can't see booking of user %s",
                            userStatus.getName(),
                            bookingOwner.getName()));
                }
        }
    }

    @Override
    public void validateUserCanDeleteBooking(Long bookingId) {
        validateUserCanEditBooking(bookingId);
    }

    @Override
    public void validateAdminCanChangeUserRole(Long userId) {
        if (!userService.getUserStatus().getId().equals(userId)) {
            throw new SecurityValidationException("admin cant change his own status");
        }
    }
}
