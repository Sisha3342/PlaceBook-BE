package com.exadel.placebook.service;

public interface SecurityValidationService {

    boolean isUserCanAddBooking(Long userId);
    boolean isUserCanEditBooking(Long userId, Long bookingId);

    boolean isUserCanDeleteBooking(Long bookingId);

    boolean isAdminCanChangeUserRole(Long userId);
}
