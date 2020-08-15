package com.exadel.placebook.controller;

import com.exadel.placebook.converter.PlaceConverter;
import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.MarkService;
import com.exadel.placebook.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@CrossOrigin
public class PlaceController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private MarkService markService;

    @GetMapping("/place/{placeId}/marks")
    public MarkDto getPlaceMarksById(@PathVariable("placeId") Long placeId) {
        return bookingService.getAverageMarks(placeId);
    }

    @GetMapping("/user/{userId}/places")
    public List<PlaceSearchDto> getPlaceByUser(@PathVariable("userId") Long userId) {
        return placeService.getPlaceByUserNow(userId);
    }

    @GetMapping("/booking/{bookingId}/mark")
    public MarkSubmitDto getMark(@PathVariable("bookingId") Long bookingId) {
        return markService.getMarksByBookingId(bookingId);
    }
    @PostMapping("/booking/{bookingId}/mark")
    public MarkSubmitDto submitMark(@PathVariable("bookingId") Long bookingId,
                                    @Valid @RequestBody MarkParams markParams, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        return markService.submitMark(bookingId, markParams);
    }

    @PostMapping("/place/{placeId}/block")
    public PlaceBlockResponse blockPlaceForUser(@PathVariable("placeId") Long placeId,
                                                @Valid @RequestBody PlaceBlockRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }

        return placeService.blockPlaceForUser(placeId, request);
    }

}
