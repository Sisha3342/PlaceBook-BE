package com.exadel.placebook.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MarkDto {
    private Double markLightning;
    private Double markAir;
    private Double markVolume;
    private Double markCleaning;
    private Double markLocation;

    public MarkDto() {
        markLightning = 0.0;
        markAir = 0.0;
        markVolume = 0.0;
        markCleaning = 0.0;
        markLocation = 0.0;
    }
}
