package com.exadel.placebook.model.sorting;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class PaginationParameters {
    @Min(0)
    private int offset = 0;
    @Min(1)
    @Positive
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
