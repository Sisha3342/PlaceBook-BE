package com.exadel.placebook.model.sorting;

import com.exadel.placebook.model.enums.OfficeSortEnum;
import com.exadel.placebook.model.enums.Order;
import lombok.Builder;

public class OfficeSorting extends PaginationParameters {
    @Builder.Default
    private Order order = Order.ASC;

    @Builder.Default
    private OfficeSortEnum officeSort = OfficeSortEnum.COUNTRY;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OfficeSortEnum getOfficeSort() {
        return officeSort;
    }

    public void setOfficeSort(OfficeSortEnum officeSort) {
        this.officeSort = officeSort;
    }
}
