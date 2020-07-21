package com.exadel.placebook.service;

import com.exadel.placebook.model.exception.AdminValidationException;
import com.exadel.placebook.model.filters.AdminUserFilter;
import org.springframework.stereotype.Component;

@Component
public class AdminUserFilterValidator {
    private boolean isValid(AdminUserFilter adminUserFilter) {
        return (adminUserFilter.getOffset() >= 0) && (adminUserFilter.getLimit() > 0);
    }

    public void validate(AdminUserFilter adminUserFilter) throws AdminValidationException {
        if (!isValid(adminUserFilter))
            throw new AdminValidationException("Fill offset and limit fields");
    }
}
