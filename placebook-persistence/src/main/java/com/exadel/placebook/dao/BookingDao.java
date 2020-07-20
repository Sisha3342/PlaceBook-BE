package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDao{

    List<Booking> findBookings(Long userId);
    List<Booking> findUserBookingsByStatus(Long id, Status status);
}
