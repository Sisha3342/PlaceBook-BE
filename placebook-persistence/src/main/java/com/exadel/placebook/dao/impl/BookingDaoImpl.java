package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingDaoImpl extends BaseDaoImpl implements BookingDao {

    @Override
    public List<Booking> findUserBookingsByStatus(Long userId, Status status) {
        Session session = getSession();
        Query<Booking> query = session
                .createQuery("from Booking b where b.user.id = :user_id and status = :status", Booking.class)
                .setParameter("user_id", userId)
                .setParameter("status", status);
        return query.list();
    }

    @Override
    public List<Booking> findBookings(Long userId) {
        Session session = getSession();
        Query<Booking> query = session
                .createQuery("from Booking b where b.userId = :user_id", Booking.class)
                .setParameter("user_id", userId);
        return query.list();
    }

    @Override
    public Map<Status, Long> getStatistics(Long userId) {
        Session session = getSession();
        return session.createQuery(
                "select b.status as status, count(b.id) from Booking b where b.user.id = :userId group by b.status", Object[].class)
                .setParameter("userId", userId)
                .stream().collect(Collectors.toMap(o -> (Status) o[0], o -> (Long) o[1]));
    }
}

