package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BookingDto {

    private Long id;
    private Long placeId;
    private User user;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private Status status;
}
