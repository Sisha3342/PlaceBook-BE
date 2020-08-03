package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface OfficeService {
    List<PlaceResponse> getPlacesByFloorId(Long floorId, LocalDateTime timeStart, LocalDateTime timeEnd);

    List<String> getAllCountries();

    List<String> getCitiesByCountry(String country);

    List<OfficeDto> getOfficesByCityAndCountry(String city, String country);

    OfficeDto addOffice(OfficeParams officeParams);

    OfficeDto editOffice(Long officeId, OfficeParams officeParams);

    OfficeDto getOffice(Long officeId);

    List<PlaceDto> getFreePlacesByFloorIdAndTimeRange(Long floorId, LocalDateTime start, LocalDateTime end);

    List<FloorDto> getFloorsByOfficeId(Long officeId);

    boolean deleteOffice(Long officeId);

    OfficeDto saveOfficeConfiguration(List<FloorDto> floors, Long officeId);
}
