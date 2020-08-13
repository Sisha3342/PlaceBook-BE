package com.exadel.placebook.model.enums;

public enum Order {
    ASC("asc"),
    DESC("desc");

    private String orderOption;

    Order(String orderOption) {
        this.orderOption = orderOption;
    }

    public String getOrderOption() {
        return orderOption;
    }
}
