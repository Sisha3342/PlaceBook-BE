package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.MarkParams;
import com.exadel.placebook.model.dto.MarkSubmitDto;
import com.exadel.placebook.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class PlaceController {
    @Autowired
    private MarkService markService;
    @PutMapping("/booking/{bookingId}/mark")
    public MarkSubmitDto submitMark(@PathVariable("bookingId") Long bookingId,
                                    @Valid@RequestBody MarkParams markParams, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        return markService.submitMark(bookingId,markParams);
    }
}
