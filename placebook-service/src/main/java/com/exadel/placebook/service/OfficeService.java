package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.FloorDto;
import com.exadel.placebook.model.dto.OfficeParams;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.dto.PlaceDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OfficeService {
    List<PlaceDto> getPlacesByFloorId(Long floorId);
    List<String> getAllCountries();
    List<String> getCitiesByCountry(String country);
    List<OfficeDto> getOfficesByCityAndCountry(String city, String country);
    OfficeDto addOffice(OfficeParams officeParams);
    OfficeDto editOffice(Long officeId,OfficeParams officeParams);

    List<PlaceDto> getFreePlacesByFloorIdAndTimeRange(Long floorId, LocalDateTime start, LocalDateTime end);

    List<FloorDto> getFloorsByOfficeId(Long officeId);
}
