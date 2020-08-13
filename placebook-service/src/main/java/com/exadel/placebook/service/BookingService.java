package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.model.sorting.BookingSorting;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface BookingService {

    List<BookingDto> findByStatus(Long id, BookingSorting bookingSorting);

    List<BookingDto> employeesBookingsByStatusAndHrId(BookingSorting bookingSorting);

    List<BookingDto> employeesBookingsByStatus(BookingSorting bookingSorting);

    List<BookingDto> findBookings(Long userId, BookingSorting bookingSorting);

    List<PlaceHistoryDto> findPlaceHistory(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd);

    Map<Status, Long> getStatistics(Long id);

    BookingInfoDto getBookingInfo(Long id);

    BookingDto findById(Long id);

    BookingDto addBooking(BookingRequest bookingRequest, Long userId);

    BookingDto editBooking(BookingRequest bookingRequest, Long bookingId);

    BookingDto deleteBooking(Long id);

    MarkDto getAverageMarks(Long placeId);

    void completeEndedBooking();
}
