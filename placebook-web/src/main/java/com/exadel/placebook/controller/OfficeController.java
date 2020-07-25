package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.exception.ValidationException;
import com.exadel.placebook.service.OfficeParamsValidator;
import com.exadel.placebook.service.OfficeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public OfficeDto addOffice(@Valid @RequestBody OfficeParams officeParams, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        officeParamsValidator.validate(officeParams);
        return officeService.addOffice(officeParams);
    }

    @PostMapping("/office/{officeId}")
    public OfficeDto editOffice(@PathVariable("officeId") Long officeId, @Valid @RequestBody OfficeParams officeParams,
                                BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        officeParamsValidator.validate(officeParams);
        return officeService.editOffice(officeId, officeParams);
    }

    @DeleteMapping("/office/{officeId}")
    public ResponseEntity<String> deteleOffice(@PathVariable("officeId") Long officeId) {
        if (officeService.deleteOffice(officeId)) {
            return ResponseEntity.ok("Office deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/office/{officeId}/floors")
    public List<FloorDto> getFloors(@PathVariable("officeId") Long officeId) {
        return officeService.getFloorsByOfficeId(officeId);
    }
}
