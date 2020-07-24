package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.enums.Status;

import java.util.List;
import java.util.Map;


public interface BookingService {

    List<BookingDto> findByStatus(Long id, Status status);
    List<BookingDto> findBookings(Long userId);
    Map<Status, Long> getStatistics(Long id);
    BookingInfoDto getBookingInfo(Long id);

    BookingDto findById(Long id);

    BookingDto addBooking(BookingRequest bookingRequest, Long userId);
    BookingDto editBooking(BookingRequest bookingRequest, Long bookingId);
    BookingDto deleteBooking(Long id);
}
