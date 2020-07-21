package com.exadel.placebook.model.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class OfficeParams {
    private String address;
    private String city;
    private String country;
    private LocalTime worktimeStart;
    private LocalTime worktimeEnd;
}
