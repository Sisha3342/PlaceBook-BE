package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingDaoImpl extends BaseDaoImpl implements BookingDao {

    @Override
    public List<Booking> findBookings(Long userId) {
        Session session = getSession();
        Query<Booking> query = session
                .createQuery("from Booking p where p.userId = :user_id", Booking.class)
                .setParameter("user_id", userId);
        return query.list();
    }

    @Override
    public List<Booking> findUserBookingsByStatus(Long userId, Status status){
        Session session = getSession();
        Query<Booking> query =session
                .createQuery("from Booking p where p.userId = :user_id and status = :status",Booking.class)
                .setParameter("user_id",userId)
                .setParameter("status",status.toString());
        return query.list();
    }
}