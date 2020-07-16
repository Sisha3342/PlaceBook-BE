package com.exadel.placebook.service;

public class PageValidation {
    public static boolean validation(Long offset, Long limit) {
        return (offset > 0) && (limit > 0);
    }
}
