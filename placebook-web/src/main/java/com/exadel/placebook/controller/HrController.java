package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HrController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/employees/bookings/all")
    public List<BookingDto> findAllUsersBookings(@RequestParam("status") Status status) {
        return bookingService.yemployeesBookingsByStatus(status);
    }

    @GetMapping("/employees/bookings")
    public List<BookingDto> findUsersBookingsByHrId(@RequestParam("status") Status status) {
        return bookingService.yemployeesBookingsByStatusAndHrId(status);
    }
}
