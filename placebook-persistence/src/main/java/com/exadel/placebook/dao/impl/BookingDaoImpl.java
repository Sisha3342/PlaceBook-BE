package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.sorting.BookingSorting;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class BookingDaoImpl extends BaseDaoImpl<Booking> implements BookingDao {

    private String getSortingParameter(BookingSorting bookingSorting) {
        String table;
        switch (bookingSorting.getBookingSort()) {
            case USER_NAME:
            case USER_SURNAME:
                table = "u." + bookingSorting.getBookingSort().getSortingOption();
                break;
            case PLACE_NUMBER:
                table = "p." + bookingSorting.getBookingSort().getSortingOption();
                break;
            case CITY:
            case ADDRESS:
            case COUNTRY:
                table = "a." + bookingSorting.getBookingSort().getSortingOption();
                break;
            case DATE_START:
            case DATE_END:
            default:
                table = "b." + bookingSorting.getBookingSort().getSortingOption();
                break;
        }
        return table;
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public List<Booking> findUserBookingsByStatus(Long userId, BookingSorting bookingSorting) {
        Session session = getSession();
        String order = bookingSorting.getOrder().getOrderOption();
        String table = getSortingParameter(bookingSorting);
        String queryStr = "from Booking b " +
                "left join fetch b.user u " +
                "left join fetch b.place p " +
                "left join fetch p.floor f " +
                "left join fetch f.office o " +
                "left join fetch o.address a " +
                "where u.id = :user_id and " +
                "b.status = :status and " +
                "o.deleted = false " +
                "order by " + table + " " + order;
        return session
                .createQuery(queryStr, Booking.class)
                .setParameter("user_id", userId)
                .setParameter("status", bookingSorting.getStatus())
                .setMaxResults(bookingSorting.getLimit())
                .setFirstResult(bookingSorting.getOffset())
                .list();
    }

    @Override
    public List<Booking> findUsersBookingsByStatus(BookingSorting bookingSorting) {
        Session session = getSession();
        String order = bookingSorting.getOrder().getOrderOption();
        String table = getSortingParameter(bookingSorting);
        Query<Booking> query = session
                .createQuery("from Booking b " +
                        "left join fetch b.place p " +
                        "left join fetch b.user u " +
                        "left join fetch p.floor f " +
                        "left join fetch f.office o " +
                        "left join fetch o.address a " +
                        "where b.status = :status " +
                        "and o.deleted = false " +
                        "order by " + table + " " + order, Booking.class)
                .setParameter("status", bookingSorting.getStatus())
                .setMaxResults(bookingSorting.getLimit())
                .setFirstResult(bookingSorting.getOffset());
        return query.list();
    }

    @Override
    public List<Booking> findUsersBookingsByHrIdAndStatus(Long id, BookingSorting bookingSorting) {
        Session session = getSession();
        String order = bookingSorting.getOrder().getOrderOption();
        String table = getSortingParameter(bookingSorting);
        Query<Booking> query = session
                .createQuery("from Booking b " +
                        "left join fetch b.place p " +
                        "left join fetch b.user u " +
                        "left join fetch p.floor f " +
                        "left join fetch f.office o " +
                        "left join fetch o.address a " +
                        "where u.hrId = :hrId " +
                        "and o.deleted = false " +
                        "and b.status = :status " +
                        "order by " + table + " " + order, Booking.class)
                .setParameter("hrId", id)
                .setParameter("status", bookingSorting.getStatus())
                .setMaxResults(bookingSorting.getLimit())
                .setFirstResult(bookingSorting.getOffset());
        return query.list();
    }

    @Override
    public List<Booking> findBookings(Long userId, BookingSorting bookingSorting) {
        Session session = getSession();
        String order = bookingSorting.getOrder().getOrderOption();
        String table = getSortingParameter(bookingSorting);
        Query<Booking> query = session
                .createQuery("from Booking b " +
                        "left join fetch b.place p " +
                        "left join fetch b.user u " +
                        "left join fetch p.floor f " +
                        "left join fetch f.office o " +
                        "left join fetch o.address a " +
                        "where u.id = :user_id " +
                        "and o.deleted = false " +
                        "order by " + table + " " + order, Booking.class)
                .setParameter("user_id", userId)
                .setMaxResults(bookingSorting.getLimit())
                .setFirstResult(bookingSorting.getOffset());
        return query.list();
    }

    @Override
    public Map<Status, Long> getStatistics(Long userId) {
        Session session = getSession();
        return session.createQuery(
                "select b.status as status, count(b.id) " +
                        "from Booking b " +
                        "join b.place p " +
                        "join p.floor f " +
                        "join f.office o " +
                        "where b.user.id = :userId " +
                        "and o.deleted = false " +
                        "group by b.status", Object[].class)
                .setParameter("userId", userId)
                .stream().collect(Collectors.toMap(o -> (Status) o[0], o -> (Long) o[1]));
    }

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public Optional<MarkDto> findMarksByPlaceId(Long id) {
        Session session = getSession();
        return session
                .createQuery("select avg(pr.markLightning) as markLightning, avg(pr.markAir) as markAir, " +
                        "avg(pr.markVolume) as markVolume, avg(pr.markCleaning) as markCleaning, " +
                        "avg(pr.markLocation) as markLocation from PlaceRate pr " +
                        "join pr.booking b " +
                        "join b.place p " +
                        "where p.id = :id")
                .setResultTransformer(Transformers.aliasToBean(MarkDto.class))
                .setParameter("id", id).uniqueResultOptional();
    }

    @Override
    public void completeEndedBookings() {
        Session session = getSession();
        Query query = session
                .createQuery("update Booking b " +
                        "set b.status = :newStatus " +
                        "where b.status = :status and b.timeEnd < CURRENT_TIMESTAMP()")
                .setParameter("status", Status.ACTIVE)
                .setParameter("newStatus", Status.COMPLETED);
        int result = query.executeUpdate();
    }

    @Override
    public List<Booking> historyByPlaceIdAndTime(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd) {
        Session session = getSession();
        Query<Booking> query = session.createQuery("from Booking b " +
                "left join fetch b.place p " +
                "left join fetch b.user u " +
                "left join fetch p.floor f " +
                "left join fetch f.office o " +
                "left join fetch o.address a where p.id = :placeId and " +
                "b.timeEnd > :timeStart and b.timeStart < :timeEnd", Booking.class)
                .setParameter("placeId", placeId)
                .setParameter("timeStart", timeStart)
                .setParameter("timeEnd", timeEnd);
        return query.list();
    }


    @Override
    public Long countBookingsByPlaceIdAndTimeRange(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd) {
        return getSession().createQuery("select count (b) from Booking b where " +
                "b.place.id = :placeId and " +
                "b.timeEnd > :timeStart and b.timeStart < :timeEnd and " +
                "b.status = 'ACTIVE'", Long.class)
                .setParameter("timeStart", timeStart)
                .setParameter("timeEnd", timeEnd)
                .setParameter("placeId", placeId)
                .getSingleResult();
    }
}