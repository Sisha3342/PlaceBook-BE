package com.exadel.placebook.model.dto;

import lombok.Data;

@Data
public class DashboardElementInfo {
    private String type;
    private String number;
    private boolean active;
    private String icon;
    private String tooltip;
    private long capacity;
    private String border;
}
