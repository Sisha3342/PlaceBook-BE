package com.exadel.placebook.service;

import com.exadel.placebook.model.exception.ParamsValidatorException;
import com.exadel.placebook.model.OfficeParams;
import org.springframework.stereotype.Component;

@Component
public class OfficeParamsValidator {
    private static boolean isValid(OfficeParams officeParams) {
        return (officeParams.getWorktimeStart().compareTo(officeParams.getWorktimeEnd())>=0)&&
                (officeParams.getCountry().isBlank())&&
                (officeParams.getCity().isBlank());
    }

    public static void validate(OfficeParams officeParams)  {
        if (!isValid(officeParams))
            throw new ParamsValidatorException("Wrong params");
    }
}
