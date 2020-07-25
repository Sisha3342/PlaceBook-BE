package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private Long placeId;
    @NotNull
    private LocalDateTime timeStart;
    @NotNull
    private LocalDateTime timeEnd;
}
