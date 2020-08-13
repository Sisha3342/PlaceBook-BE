package com.exadel.placebook.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserSortEnum {
    @JsonProperty("userName")
    USER_NAME("name"),
    @JsonProperty("surname")
    USER_SURNAME("surname"),
    @JsonProperty("email")
    USER_EMAIl("email"),
    @JsonProperty("role")
    USER_ROLE("role");

    private String sortingOption;

    UserSortEnum(String sortingOption) {
        this.sortingOption = sortingOption;
    }

    public String getSortingOption() {
        return sortingOption;
    }
}
