package com.exadel.placebook.model.sorting;

import com.exadel.placebook.model.enums.BookingSortEnum;
import com.exadel.placebook.model.enums.Order;
import com.exadel.placebook.model.enums.Status;

public class BookingSorting extends PaginationParameters {
    private Order order = Order.ASC;

    private BookingSortEnum bookingSort = BookingSortEnum.DATE_START;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    public BookingSortEnum getBookingSort() {
        return bookingSort;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setBookingSort(BookingSortEnum bookingSort) {
        this.bookingSort = bookingSort;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
