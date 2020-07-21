package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingInfoDto {

    private Long id;
    private String placeInfo;
    private String userName;
    private String userSurname;
    private MarkDto markDto;
    private String address;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Status status;
}
