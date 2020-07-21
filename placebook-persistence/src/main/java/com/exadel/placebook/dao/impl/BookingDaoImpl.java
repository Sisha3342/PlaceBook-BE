package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingDaoImpl extends BaseDaoImpl implements BookingDao {

    @Override
    public List<Booking> findBookings(Long userId) {
        Session session = getSession();
        Query<Booking> query = session
                .createQuery("from Booking b where b.user.id = :user_id", Booking.class)
                .setParameter("user_id", userId);
        return query.list();
    }

    @Override
    public List<Booking> findUserBookingsByStatus(Long userId, Status status) {
        Session session = getSession();
        Query<Booking> query = session
                .createQuery("from Booking b where b.user.id = :user_id and status = :status", Booking.class)
                .setParameter("user_id", userId)
                .setParameter("status", status.toString());
        return query.list();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        Session session = getSession();
        return session
                .createQuery("from Booking b where b.id = :id", Booking.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public Optional<MarkDto> findByMarksByPlaceId(Long id) {
        Session session = getSession();
        return session
                .createQuery("select avg(pr.markLightning) as markLightning, avg(pr.markAir) as markAir, " +
                        "avg(pr.markVolume) as markVolume, avg(pr.markCleaning) as markCleaning, " +
                        "avg(pr.markLocation) as markLocation from PlaceRate pr " +
                        "join pr.booking b " +
                        "join b.place p " +
                        "where p.id = :id")
                .setResultTransformer(Transformers.aliasToBean(MarkDto.class))
                .setParameter("id",id).uniqueResultOptional();
    }
}