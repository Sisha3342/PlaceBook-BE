package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.exception.ValidationException;
import com.exadel.placebook.service.OfficeParamsValidator;
import com.exadel.placebook.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private OfficeParamsValidator officeParamsValidator;

    @GetMapping("/floor/{floorId}/places")
    public List<PlaceDto> getPlacesByFloorId(@PathVariable("floorId") Long floorId) {
        return officeService.getPlacesByFloorId(floorId);
    }

    @GetMapping("/floor/{floorId}/freePlaces")
    public List<PlaceDto> getFreePlaces(@PathVariable("floorId") Long floorId,
                                             @RequestParam("timeStart")
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                                             @RequestParam("timeEnd")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeEnd) {
        return officeService.getFreePlacesByFloorIdAndTimeRange(floorId, timeStart, timeEnd);
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

    @PostMapping("/office")
    public OfficeDto addOffice(@Valid @RequestBody OfficeParams officeParams,BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        officeParamsValidator.validate(officeParams);
        return officeService.addOffice(officeParams);
    }

    @PutMapping("/office/{officeId}")
    public OfficeDto editOffice(@PathVariable("officeId") Long officeId,@Valid@RequestBody OfficeParams officeParams,
    BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        officeParamsValidator.validate(officeParams);
        return officeService.editOffice(officeId, officeParams);
    }

    @GetMapping("/office/{officeId}/floors")
    public List<FloorDto> getFloors(@PathVariable("officeId") Long officeId) {
        return officeService.getFloorsByOfficeId(officeId);
    }

    @DeleteMapping("/office/{officeId}")
    public ResponseEntity<String> deleteOffice(@PathVariable("officeId") Long officeId) {
        if (officeService.deleteOffice(officeId)) {
            return ResponseEntity.ok("Office deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("office/{officeId}/configuration")
    public OfficeDto saveOfficeConfiguration(@RequestBody List<FloorRequest> floors,
                                             @PathVariable("officeId") Long officeId) {
        return officeService.saveOfficeConfiguration(floors, officeId);
    }

    @PutMapping("office/{officeId}/configuration")
    public OfficeDto editOfficeConfiguration(@RequestBody List<FloorRequest> floors,
                                             @PathVariable("officeId") Long officeId) {
        return officeService.editOfficeConfiguration(floors, officeId);
    }
}
