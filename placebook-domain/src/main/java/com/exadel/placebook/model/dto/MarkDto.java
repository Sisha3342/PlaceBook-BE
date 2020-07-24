package com.exadel.placebook.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class MarkDto {


    private double markLightning;

    private double markAir;

    private double markVolume;

    private double markCleaning;
    private double markLocation;
}
