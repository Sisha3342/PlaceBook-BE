package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.enums.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface BookingService {

    List<BookingDto> findByStatus(Long Id, Status status);
    List<BookingDto> findBookings(Long userId);
    Map<Status, Long> getStatistics(Long id);
    BookingInfoDto findBookingInfoModalPage(Long id);

}
