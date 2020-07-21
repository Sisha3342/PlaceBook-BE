package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingInfoController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/{id}/info")
    public BookingInfoDto findBookingInfo(@PathVariable Long id) {
        return bookingService.findBookingInfoModalPage(id);
    }
}