package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.SecurityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SecurityValidationService securityValidationService;

    @GetMapping("/user/{userId}/bookings")
    public List<BookingDto> findUserBookingsActive(@PathVariable("userId") Long userId, @RequestParam Status status) {
        return bookingService.findByStatus(userId, status);
    }

    @GetMapping("/user/{userId}/booking/statistics")
    public Map<Status, Long> getStatistics(@PathVariable("userId") Long userId) {
        return bookingService.getStatistics(userId);
    }

    @GetMapping("/user/{userId}/booking/{bookingId}/info")
    public BookingInfoDto findBookingInfo(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        return bookingService.getBookingInfo(bookingId);
    }

    @PutMapping("/user/{userId}/booking")
    public BookingDto addBooking(@RequestBody BookingRequest bookingRequest, @PathVariable("userId") Long userId) {
        securityValidationService.validateUserCanAddBooking(userId);

        return bookingService.addBooking(bookingRequest, userId);
    }

    @PostMapping("/user/{userId}/{bookingId}")
    public BookingDto editBooking(@RequestBody BookingRequest bookingRequest,
                                  @PathVariable("userId") Long userId,
                                  @PathVariable("bookingId") Long bookingId) {
        securityValidationService.validateUserCanEditBooking(userId, bookingId);

        return bookingService.editBooking(bookingRequest, userId, bookingId);
    }

    @DeleteMapping("/user/booking/{bookingId}")
    public BookingDto deleteBooking(@PathVariable("bookingId") Long bookingId) {
        securityValidationService.validateUserCanDeleteBooking(bookingId);

        return bookingService.deleteBooking(bookingId);
    }
}