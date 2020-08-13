package com.exadel.placebook.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BookingSortEnum {
    @JsonProperty(value = "placeName")
    PLACE_NUMBER("placeNumber"),
    @JsonProperty(value = "timeStart")
    DATE_START("timeStart"),
    @JsonProperty(value = "timeEnd")
    DATE_END("timeEnd"),
    @JsonProperty(value = "country")
    COUNTRY("country"),
    @JsonProperty(value = "city")
    CITY("city"),
    @JsonProperty(value = "address")
    ADDRESS("address");

    private String sortingOption;

    public void setSortingOption(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    BookingSortEnum(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    public String getSortingOption() {
        return sortingOption;
    }
}
