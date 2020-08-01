package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class PlaceResponse {
    private Long placeId;
    private String placeNumber;
    private boolean occupied;
}
