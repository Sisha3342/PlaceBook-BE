package com.exadel.placebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class SubscribeProcessor {
    @Autowired
    private PlaceService placeService;

    @Scheduled(fixedDelay = 3600000)
    public void scheduledBookingStatusChange() {
        placeService.subscribeChecker();
    }
}
