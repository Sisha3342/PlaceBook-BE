package com.exadel.placebook.service;

import com.exadel.placebook.model.filters.AdminUserFilter;


public class PageValidation {
    private static boolean isValid(AdminUserFilter adminUserFilter) {
        return (adminUserFilter.getOffset() >= 0) && (adminUserFilter.getLimit() > 0);
    }

    public static void validate(AdminUserFilter adminUserFilter) throws Exception {
        if (!isValid(adminUserFilter))
            throw new Exception("message");
    }
}
