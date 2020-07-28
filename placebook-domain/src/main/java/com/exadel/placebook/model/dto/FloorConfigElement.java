package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class FloorConfigElement {
    private int x;
    private int y;
    private int cols;
    private int rows;
    private FloorConfigElementInfo data;
}
