package com.exadel.placebook.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {

    private Long placeId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
}
