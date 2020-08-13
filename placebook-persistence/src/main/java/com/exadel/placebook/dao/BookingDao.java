package com.exadel.placebook.dao;

import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.sorting.BookingSorting;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingDao extends BaseDao<Booking> {

    List<Booking> findBookings(Long userId, BookingSorting bookingSorting);

    List<Booking> findUserBookingsByStatus(Long id, BookingSorting bookingSorting);

    List<Booking> findUsersBookingsByHrIdAndStatus(Long id, BookingSorting bookingSorting);

    List<Booking> findUsersBookingsByStatus(BookingSorting bookingSorting);

    Map<Status, Long> getStatistics(Long userId);

    Optional<MarkDto> findMarksByPlaceId(Long id);

    List<Booking> historyByPlaceIdAndTime(Long PlaceId, LocalDateTime timeStart, LocalDateTime timeEnd);

    void completeEndedBookings();

    Long countBookingsByPlaceIdAndTimeRange(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd);
}
