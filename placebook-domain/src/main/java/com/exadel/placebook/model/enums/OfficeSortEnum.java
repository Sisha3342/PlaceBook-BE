package com.exadel.placebook.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OfficeSortEnum {
    @JsonProperty("country")
    COUNTRY("country"),
    @JsonProperty("city")
    CITY("city"),
    @JsonProperty(value = "address")
    ADDRESS("address");

    private String sortingOption;

    OfficeSortEnum(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    public String getSortingOption() {
        return sortingOption;
    }
}
