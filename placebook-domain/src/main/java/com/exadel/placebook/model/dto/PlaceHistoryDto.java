package com.exadel.placebook.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class PlaceHistoryDto {
    private Long id;
    private String userName;
    private String userSurname;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
}
