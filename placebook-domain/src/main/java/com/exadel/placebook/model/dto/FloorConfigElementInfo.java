package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class FloorConfigElementInfo {
    private String type;
    private String number;
    private boolean active;
    private String icon;
    private String tooltip;
}
