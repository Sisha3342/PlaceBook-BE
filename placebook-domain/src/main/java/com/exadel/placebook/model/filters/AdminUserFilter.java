package com.exadel.placebook.model.filters;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class AdminUserFilter {
    @Min(0)
    private int offset;
    @Min(1)
    @Positive
    private int limit;
    private String text;

    public AdminUserFilter() {
    }

    public AdminUserFilter(int limit, int offset, String text) {
        this.limit = limit;
        this.offset = offset;
        this.text = text;
    }

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
