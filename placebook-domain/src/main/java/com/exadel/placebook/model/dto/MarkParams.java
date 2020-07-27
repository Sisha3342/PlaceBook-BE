package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Data
public class MarkParams {
    @Min(1)
    @Max(5)
    private Long markLightning;
    @Min(1)
    @Max(5)
    private Long markAir;
    @Min(1)
    @Max(5)
    private Long markVolume;
    @Min(1)
    @Max(5)
    private Long markCleaning;
    @Min(1)
    @Max(5)
    private Long markLocation;
    private String feedBack;
}
