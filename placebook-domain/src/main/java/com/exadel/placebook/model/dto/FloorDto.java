package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FloorDto {
    private Long id;
    @NotBlank
    private String floorNumber;
    @NotNull
    private String floorConfiguration;
    private Long width;
    private Long height;
}
