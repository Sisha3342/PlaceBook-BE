package com.exadel.placebook.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailMessageDto {

    private String placeNumber;
    private String userName;
    private String email;
    private String country;
    private String city;
    private String office;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

}
