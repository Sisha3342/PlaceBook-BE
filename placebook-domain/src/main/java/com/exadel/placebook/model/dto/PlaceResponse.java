package com.exadel.placebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceResponse {
    private Long placeId;
    private String placeNumber;
    private boolean occupied;
}
