package com.exadel.placebook.model.enums;

public enum BookingSortEnum {
    PLACE_NAME("placeName"),
    DATE_START("timeStart"),
    DATE_END("timeEnd"),
    COUNTRY("country"),
    CITY("city"),
    ADDRESS("address");

    private String sortingOption;

    BookingSortEnum(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    public String getSortingOption() {
        return sortingOption;
    }
}
