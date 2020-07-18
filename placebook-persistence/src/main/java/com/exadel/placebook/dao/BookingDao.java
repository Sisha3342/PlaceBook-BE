package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao {

    List<Booking> findBookings(Long userId);

    List<Booking> findUserBookingsByStatus(Long userId, Status status);
}
