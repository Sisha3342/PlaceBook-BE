package com.exadel.placebook.service;


import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.enums.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingService {

    List<BookingDto> findBookings(Long userId);

    Map<String, Integer> statistics(Long id);

    List<BookingDto> findByStatus(Long id, Status status);

    Optional<BookingInfoDto> findBookingInfo(Long id);
}
