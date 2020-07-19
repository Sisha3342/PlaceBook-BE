package com.exadel.placebook.controller;


import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/active")
    public List<BookingDto> findUserBookingsActive(@RequestParam Long id, @RequestParam Status status) {
        return bookingService.findByStatus(id, status);
    }

    @GetMapping("/booking/statistics")
    public Map<String, Integer> statistics(@RequestParam Long id) {
        return bookingService.statistics(id);
    }
    @GetMapping("/booking/info")
    public List<BookingDto> getBookingInfo(@RequestParam Long id) {
        return bookingService.findBookings(id);
    }
}
