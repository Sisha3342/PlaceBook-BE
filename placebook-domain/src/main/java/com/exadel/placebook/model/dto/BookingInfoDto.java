package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BookingInfoDto {

    private Long id;
    private String placeInfo;
    private UserDto userDto;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private Status status;

}
