package com.exadel.placebook.service;

public interface SecurityValidationService {

    void validateUserCanAddBooking(Long userId);
    void validateUserCanEditBooking(Long bookingId);

    void validateUserCanDeleteBooking(Long bookingId);

    void validateAdminCanChangeUserRole(Long userId);
}
