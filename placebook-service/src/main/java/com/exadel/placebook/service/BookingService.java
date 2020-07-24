package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.enums.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface BookingService {

    List<BookingDto> findByStatus(Long id, Status status);
    List<BookingDto> findBookings(Long userId);
    Map<Status, Long> getStatistics(Long id);
    BookingInfoDto getBookingInfo(Long id);
    List<String> getAllCountries();
    List<String> getAllCitiesByCountry(String country);
    List<OfficeDto> getAllOfficesByCity(String city);
    MarkDto getMarksByPlaceId(Long id);
    void completeEndedBooking();
}
