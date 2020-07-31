package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.dto.PlaceHistoryDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.model.exception.ValidationException;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.SecurityValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SecurityValidationService securityValidationService;

    @GetMapping("/user/{userId}/bookings")
    public List<BookingDto> findUserBookingsActive(@PathVariable("userId") Long userId, @RequestParam("status") Status status) {
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

    @GetMapping("/user/{userId}/booking/all")
    public List<BookingDto> getAllBookingsByUserId(@PathVariable("userId") Long userId) {
        return bookingService.findBookings(userId);
    }

    @PostMapping("/user/{userId}/booking")
    public BookingDto addBooking(@Valid @RequestBody BookingRequest bookingRequest, @PathVariable("userId") Long userId
            , BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        securityValidationService.validateUserCanAddBooking(userId);

        return bookingService.addBooking(bookingRequest, userId);
    }

    @PutMapping("/user/booking/{bookingId}")
    public BookingDto editBooking(@Valid @RequestBody BookingRequest bookingRequest,
                                  @PathVariable("bookingId") Long bookingId,
                                  BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        securityValidationService.validateUserCanEditBooking(bookingId);

        return bookingService.editBooking(bookingRequest, bookingId);
    }

    @DeleteMapping("/user/booking/{bookingId}")
    public BookingDto deleteBooking(@PathVariable("bookingId") Long bookingId) {
        securityValidationService.validateUserCanDeleteBooking(bookingId);

        return bookingService.deleteBooking(bookingId);
    }

    @GetMapping("/place/{placeId}/bookings")
    public List<PlaceHistoryDto> placeHistory(@PathVariable("placeId") Long placeId,
                                              @RequestParam("timeStart")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                                              @RequestParam("timeEnd")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeEnd) {
        return bookingService.findPlaceHistory(placeId, timeStart, timeEnd);
    }
}