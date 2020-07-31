package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private LocalDateTime timeStart;
    @NotNull
    private LocalDateTime timeEnd;
    @NotBlank
    private String placeNumber;
    @NotBlank
    private String floorNumber;
    @NotNull
    private long officeId;
}
