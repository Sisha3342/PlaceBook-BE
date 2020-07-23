package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Data
public class OfficeDto {
    private Long id;
    private AddressDto address;
    private LocalTime worktimeStart;
    private LocalTime worktimeEnd;
}