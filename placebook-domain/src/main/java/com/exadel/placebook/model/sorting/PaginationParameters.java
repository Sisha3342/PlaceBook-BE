package com.exadel.placebook.model.sorting;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class PaginationParameters {
    @Min(0)
    @Builder.Default
    private int offset = 0;
    @Min(1)
    @Positive
    @Builder.Default
    private int limit = 10;

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
}
