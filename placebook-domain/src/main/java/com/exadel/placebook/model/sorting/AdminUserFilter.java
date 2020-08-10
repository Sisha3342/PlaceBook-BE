package com.exadel.placebook.model.sorting;

import com.exadel.placebook.model.enums.BookingSortEnum;
import com.exadel.placebook.model.enums.Order;
import com.exadel.placebook.model.enums.UserSortEnum;
import lombok.Builder;

public class AdminUserFilter extends PaginationParameters {
    @Builder.Default
    private Order order = Order.ASC;

    @Builder.Default
    private UserSortEnum userSort = UserSortEnum.USER_SURNAME;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserSortEnum getUserSort() {
        return userSort;
    }

    public void setUserSort(UserSortEnum userSort) {
        this.userSort = userSort;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
