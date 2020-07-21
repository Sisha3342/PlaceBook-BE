package com.exadel.placebook.controller;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/user/{userId}/bookings/statistics")
    public Map<Status, Long> getStatistics(@PathVariable Long userId) {
        return bookingService.getStatistics(userId);
    }


}
