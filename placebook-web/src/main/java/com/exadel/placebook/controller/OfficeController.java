package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.service.OfficeParamsValidator;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private OfficeParamsValidator officeParamsValidator;

    @GetMapping("/floor/{floorId}/places")
    public List<PlaceDto> getPlacesByFloorId(@PathVariable("floorId") Long floorId) {
        return officeService.getPlacesByFloorId(floorId);
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        return officeService.getAllCountries();
    }

    @GetMapping("/countries/{country}/cities")
    public List<String> getAllCities(@PathVariable("country") String country) {
        return officeService.getCitiesByCountry(country);
    }

    @GetMapping("/countries/{country}/cities/{city}/offices")
    public List<OfficeDto> getOfficesByCityAndCountry(@PathVariable("country") String country, @PathVariable("city") String city) {
        return officeService.getOfficesByCityAndCountry(city, country);
    }

    @PutMapping("/office")
    public OfficeDto addOffice(@RequestBody OfficeParams officeParams) {
        officeParamsValidator.validate(officeParams);
        return officeService.addOffice(officeParams);
    }

    @PostMapping("/office/{officeId}")
    public OfficeDto editOffice(@PathVariable("officeId") Long officeId,@RequestBody OfficeParams officeParams) {
        officeParamsValidator.validate(officeParams);
        return officeService.editOffice(officeId, officeParams);
    }
}
