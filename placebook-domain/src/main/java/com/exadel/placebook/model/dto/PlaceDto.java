package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class PlaceDto {
    private Long placeId;
    private String placeNumber;
    private Boolean isActive;
}
