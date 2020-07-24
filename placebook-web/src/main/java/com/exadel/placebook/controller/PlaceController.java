package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/place/{placeId}/marks")
    public MarkDto getPlaceMarksById(@PathVariable("placeId") Long placeId) {
        return bookingService.getMarksByPlaceId(placeId);
    }
}
