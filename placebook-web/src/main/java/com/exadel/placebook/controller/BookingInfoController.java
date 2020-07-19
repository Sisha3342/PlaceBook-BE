package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.service.BookingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingInfoController {

    @Autowired
    private BookingInfoService bookingInfoService;

    @GetMapping("/bookingInfo")
    public BookingInfoDto findBookingInfo(@RequestParam Long id) {
        return bookingInfoService.findBookingInfo(id);
    }
}
