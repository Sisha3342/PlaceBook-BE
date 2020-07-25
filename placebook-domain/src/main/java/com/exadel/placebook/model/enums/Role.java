package com.exadel.placebook.model.enums;

import java.util.Arrays;

public enum Role {
    ADMIN,
    USER,
    HR,
    EDITOR;

    public boolean in(Role... roles) {
        return Arrays.asList(roles).contains(this);
    }
}
