package com.exadel.placebook.model.enums;

import java.util.Arrays;

public enum FloorConfigElementType {
    DESK("desk"),
    CONSTANT("constant"),
    MEETING_ROOM("meetingRoom"),
    KITCHEN("kitchen"),
    TOILET("toilet"),
    DOOR("door"),
    WINDOW("window");

    private String simpleName;

    FloorConfigElementType(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public static boolean isInTypesSimpleNames(String input, FloorConfigElementType... types) {
        return Arrays.stream(types).anyMatch(type -> type.getSimpleName().equals(input));
    }
}
