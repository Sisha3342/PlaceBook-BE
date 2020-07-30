package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface BookingService {

    List<BookingDto> findByStatus(Long id, Status status);
    List<BookingDto> employeesBookingsByStatusAndHrId(Status status);
    List<BookingDto> employeesBookingsByStatus(Status status);
    List<BookingDto> findBookings(Long userId);

    List<PlaceHistoryDto> findPlaceHistory(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd);

    Map<Status, Long> getStatistics(Long id);

    BookingInfoDto getBookingInfo(Long id);

    BookingDto findById(Long id);

    MarkDto getMarksByPlaceId(Long id);

    BookingDto addBooking(BookingRequest bookingRequest, Long userId);

    BookingDto editBooking(BookingRequest bookingRequest, Long bookingId);

    BookingDto deleteBooking(Long id);

    MarkDto getAverageMarks(Long placeId);

    void completeEndedBooking();
}
