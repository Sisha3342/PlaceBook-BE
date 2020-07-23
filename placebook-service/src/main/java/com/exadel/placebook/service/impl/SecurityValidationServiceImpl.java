package com.exadel.placebook.service.impl;

import com.exadel.placebook.dao.BookingDao;
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
    UserService userService;

    @Autowired
    BookingDao bookingDao;

    @Override
    public boolean isUserCanAddBooking(Long userId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(userId);

        return currentUserStatus.getRole().equals(Role.ADMIN) ||
                (currentUserStatus.getRole().equals(Role.HR) &&
                userDto.getHrId().equals(currentUserStatus.getId())) ||
                ((currentUserStatus.getRole().equals(Role.USER) ||
                        currentUserStatus.getRole().equals(Role.EDITOR)) &&
                        currentUserStatus.getId().equals(userId));
    }

    @Override
    public boolean isUserCanEditBooking(Long userId, Long bookingId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();
        UserDto userDto = userService.findById(userId);
        Booking booking = bookingDao.find(bookingId);

        return currentUserStatus.getRole().equals(Role.ADMIN) ||
                (currentUserStatus.getRole().equals(Role.HR) &&
                        (booking.getUser().getHrId().equals(currentUserStatus.getId()) &&
                        userDto.getHrId().equals(currentUserStatus.getId()))) ||
                                booking.getUser().getId().equals(currentUserStatus.getId());
    }

    @Override
    public boolean isUserCanDeleteBooking(Long bookingId) {
        UserStatusDto currentUserStatus = userService.getUserStatus();

        return isUserCanEditBooking(currentUserStatus.getId(), bookingId);
    }

    @Override
    public boolean isAdminCanChangeUserRole(Long userId) {
        return !userService.getUserStatus().getId().equals(userId);
    }
}
