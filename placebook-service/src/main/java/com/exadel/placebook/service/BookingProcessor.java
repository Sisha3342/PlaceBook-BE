package com.exadel.placebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class BookingProcessor {
    @Autowired
    private BookingService bookingService;

    @Scheduled(fixedDelay = 3600000)
    public void scheduledBookingStatusChange() {
        bookingService.completeEndedBooking();
    }
}
