package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.converter.BookingInfoConverter;
import com.exadel.placebook.converter.PlaceHistoryConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.exception.BookingException;
import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.PlaceStatus;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.model.exception.EntityNotFoundException;
import com.exadel.placebook.model.sorting.BookingSorting;
import com.exadel.placebook.model.security.UserContext;
import com.exadel.placebook.service.BookingService;
import com.exadel.placebook.service.MarkService;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BookingConverter bookingConverter;

    @Autowired
    private BookingInfoConverter bookingInfoConverter;

    @Autowired
    private PlaceHistoryConverter placeHistoryConverter;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private MarkService markService;

    @Override
    public List<BookingDto> findBookings(Long userId, BookingSorting bookingSorting) {
        List<Booking> list = bookingDao.findBookings(userId, bookingSorting);
        return list.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public MarkDto getAverageMarks(Long placeId) {
        return bookingDao.findMarksByPlaceId(placeId).orElse(new MarkDto());
    }

    @Override
    public BookingInfoDto getBookingInfo(Long id) {
        Booking booking = bookingDao.find(id);
        return bookingInfoConverter.convert(booking, markService.getMarksByBookingId(id));
    }

    @Override
    public void completeEndedBooking() {
        bookingDao.completeEndedBookings();
    }

    @Override
    public List<BookingDto> employeesBookingsByStatusAndHrId(BookingSorting bookingSorting) {
        UserContext context = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Booking> bookingList = bookingDao.findUsersBookingsByHrIdAndStatus(context.getUserDto().getId(), bookingSorting);
        return bookingList.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> employeesBookingsByStatus(BookingSorting bookingSorting) {
        List<Booking> bookingList = bookingDao.findUsersBookingsByStatus(bookingSorting);
        return bookingList.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> findByStatus(Long id, BookingSorting bookingSorting) {
        List<Booking> bookingList = bookingDao.findUserBookingsByStatus(id, bookingSorting);
        return bookingList.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Map<Status, Long> getStatistics(Long id) {
        return bookingDao.getStatistics(id);
    }

    @Override
    public BookingDto findById(Long id) {
        return bookingConverter.convert(bookingDao.find(id));
    }

    @Override
    public BookingDto addBooking(BookingRequest bookingRequest, Long userId) {
        Place place = getAvailablePlace(bookingRequest);

        Booking booking = new Booking();
        booking.setPlace(place);
        booking.setStatus(Status.ACTIVE);
        booking.setTimeStart(bookingRequest.getTimeStart());
        booking.setTimeEnd(bookingRequest.getTimeEnd());
        booking.setUser(userDao.load(userId));
        return bookingConverter.convert(bookingDao.save(booking));
    }

    @Override
    public BookingDto editBooking(BookingRequest bookingRequest, Long bookingId) {
        Place place = getAvailablePlace(bookingRequest);
        Booking booking = bookingDao.load(bookingId);

        if (!booking.getStatus().equals(Status.ACTIVE)) {
            throw new BookingException(String.format("booking %d is inactive", booking.getId()));
        }

        booking.setPlace(place);
        booking.setTimeStart(bookingRequest.getTimeStart());
        booking.setTimeEnd(bookingRequest.getTimeEnd());

        return bookingConverter.convert(bookingDao.update(booking));
    }

    @Override
    public BookingDto deleteBooking(Long id) {
        Booking booking = bookingDao.find(id);

        if (booking == null) {
            throw new EntityNotFoundException(Booking.class, id);
        }

        booking.setStatus(Status.CANCELED);

        return bookingConverter.convert(bookingDao.save(booking));
    }

    private Place getAvailablePlace(BookingRequest bookingRequest) {
        Place place = placeDao.find(bookingRequest.getPlaceId());

        if (place == null) {
            throw new EntityNotFoundException(Place.class, bookingRequest.getPlaceId());
        }

        if (place.getPlaceStatus().equals(PlaceStatus.INACTIVE)) {
            throw new BookingException(String.format("place %s is inactive", place.getPlaceNumber()));
        }

        long placeBookingsNumber = bookingDao.countBookingsByPlaceIdAndTimeRange(bookingRequest.getPlaceId(),
                bookingRequest.getTimeStart(),
                bookingRequest.getTimeEnd());

        if (placeBookingsNumber != 0) {
            throw new BookingException(String.format("place %d is occupied", bookingRequest.getPlaceId()));
        }

        return place;
    }

    @Override
    public List<PlaceHistoryDto> findPlaceHistory(Long placeId, LocalDateTime timeStart, LocalDateTime timeEnd) {
        List<Booking> list = bookingDao.historyByPlaceIdAndTime(placeId, timeStart, timeEnd);
        return list.stream().map(placeHistoryConverter::convert).collect(Collectors.toList());
    }
}