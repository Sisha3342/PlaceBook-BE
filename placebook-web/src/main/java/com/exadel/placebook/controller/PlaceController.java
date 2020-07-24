package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/place/{placeId}/marks")
    public MarkDto getPlaceMarksById(@PathVariable("placeId") Long placeId) {
        return bookingService.getMarksByPlaceId(placeId);
    }

    @GetMapping("/user/{userId}/places")
    public List<PlaceSearchDto> getPlaceByUser(@PathVariable("userId") Long userId) {
        return placeService.getPlaceByUserNow(userId);
    }
}
