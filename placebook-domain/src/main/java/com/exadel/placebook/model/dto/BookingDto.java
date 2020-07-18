package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class BookingDto {
    private Long id;
    private Long placeId;
    private Long userId;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Status status;

}
