package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.service.OfficeParamsValidator;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OfficeController {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private OfficeParamsValidator officeParamsValidator;

    @PutMapping("/office")
    public OfficeDto addOffice(@Valid OfficeParams officeParams) {
        officeParamsValidator.validate(officeParams);
        return officeService.addOffice(officeParams);
    }

    @PostMapping("/office/{officeId}")
    public OfficeDto editOffice(@PathVariable("officeId") Long officeId, OfficeParams officeParams) {
        officeParamsValidator.validate(officeParams);
        return officeService.editOffice(officeId, officeParams);
    }
}
