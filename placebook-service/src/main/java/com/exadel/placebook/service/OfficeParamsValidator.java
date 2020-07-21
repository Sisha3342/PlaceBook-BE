package com.exadel.placebook.service;

import com.exadel.placebook.exception.ParamsValidatorException;
import com.exadel.placebook.model.OfficeParams;
import org.springframework.stereotype.Component;

@Component
public class OfficeParamsValidator {
    private static boolean isValid(OfficeParams officeParams) {
        return (officeParams.getWorktimeStart().compareTo(officeParams.getWorktimeEnd())>=0);
    }

    public static void validate(OfficeParams officeParams)  {
        if (!isValid(officeParams))
            throw new ParamsValidatorException("Wrong params");
    }
}
