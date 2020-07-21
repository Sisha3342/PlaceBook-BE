package com.exadel.placebook.service;

import com.exadel.placebook.model.exception.ParamsValidationException;
import com.exadel.placebook.model.OfficeParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class OfficeParamsValidator {
    private static boolean isValid(OfficeParams officeParams) {
        return (officeParams.getWorktimeStart().compareTo(officeParams.getWorktimeEnd())>=0)&&
                ( StringUtils.isNotBlank(officeParams.getCountry()))&&
                ( StringUtils.isNotBlank(officeParams.getCity()));
    }

    public static void validate(OfficeParams officeParams)  {
        if (!isValid(officeParams))
            throw new ParamsValidationException("Wrong params");
    }
}
