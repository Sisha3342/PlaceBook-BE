package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    private Long id;
    private Long userId;
    private String photoUrl;
    private String placeNumber;
    private String userName;
    private String userSurname;
    private String email;
    private AddressDto address;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Status status;
}

