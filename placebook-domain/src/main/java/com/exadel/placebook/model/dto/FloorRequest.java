package com.exadel.placebook.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FloorRequest {
    @NotNull
    private List<FloorConfigElement> configurationList;
    @NotBlank
    private String floorNumber;
}
