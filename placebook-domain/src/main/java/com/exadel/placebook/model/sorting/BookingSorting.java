package com.exadel.placebook.model.sorting;

import com.exadel.placebook.model.enums.BookingSortEnum;
import com.exadel.placebook.model.enums.Order;
import com.exadel.placebook.model.enums.Status;
import lombok.Builder;

public class BookingSorting extends PaginationParameters {
    @Builder.Default
    private Order order = Order.ASC;

    @Builder.Default
    private BookingSortEnum bookingSortEnum = BookingSortEnum.DATE_START;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    public BookingSortEnum getBookingSort() {
        return bookingSortEnum;
    }


}
