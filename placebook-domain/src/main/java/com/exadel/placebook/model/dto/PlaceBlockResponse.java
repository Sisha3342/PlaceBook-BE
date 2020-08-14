package com.exadel.placebook.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaceBlockResponse {
    private Long userId;
    private LocalDateTime blockEnd;
    private Long placeId;
}
