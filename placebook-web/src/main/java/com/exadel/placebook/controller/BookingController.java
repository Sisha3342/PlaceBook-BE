package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

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
        return bookingService.addBooking(bookingRequest, userId);
    }

    @PostMapping("/user/{userId}/{bookingId}/edit")
    public BookingDto editBooking(@RequestBody BookingRequest bookingRequest,
                                  @PathVariable("userId") Long userId,
                                  @PathVariable("bookingId") Long bookingId) {
        return bookingService.editBooking(bookingRequest, userId, bookingId);
    }
}