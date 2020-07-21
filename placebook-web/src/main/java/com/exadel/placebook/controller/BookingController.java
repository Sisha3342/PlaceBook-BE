package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/user/{userId}/{status}/bookings")
    public List<BookingDto> findUserBookingsActive(@PathVariable Long userId, @PathVariable Status status) {
        return bookingService.findByStatus(userId, status);
    }

    @GetMapping("/booking/{userId}/statistics")
    public Map<Status, Long> getStatistics(@PathVariable Long userId) {
        return bookingService.getStatistics(userId);
    }
    @GetMapping("/booking/{id}/info")
    public BookingInfoDto findBookingInfo(@PathVariable Long id) {
        return bookingService.findBookingInfoModalPage(id);
    }
}
