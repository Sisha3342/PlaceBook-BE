package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SubscribeController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/subscribe/{placeId}")
    public PlaceDto subscribeToPlace(@PathVariable("placeId") Long placeId) {
        return placeService.subscribeToPlace(placeId);
    }
}
