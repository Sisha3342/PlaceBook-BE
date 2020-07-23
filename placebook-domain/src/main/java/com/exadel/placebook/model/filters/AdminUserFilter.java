package com.exadel.placebook.model.filters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AdminUserFilter {
    @Min(0)
    @NotBlank
    private int offset;
    @Min(1)
    @NotBlank
    private int limit;
    private String text;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
