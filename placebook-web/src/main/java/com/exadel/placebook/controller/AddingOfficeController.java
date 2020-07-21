package com.exadel.placebook.controller;

import com.exadel.placebook.model.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.service.OfficeParamsValidator;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddingOfficeController {

    @Autowired
    private OfficeService officeService;

    @PutMapping("/office")
    public OfficeDto addOffice(OfficeParams officeParams) {
        OfficeParamsValidator.validate(officeParams);
       return officeService.addOffice(officeParams);
    }

     @PostMapping("/office/{officeId}")
    public OfficeDto editOffice(@PathVariable("officeId") Long officeId, OfficeParams officeParams) {
        OfficeParamsValidator.validate(officeParams);
        return officeService.editOffice(officeId,officeParams);
    }
}
