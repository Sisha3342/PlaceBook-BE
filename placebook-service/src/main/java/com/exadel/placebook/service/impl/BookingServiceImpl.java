package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.converter.BookingInfoConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.exception.BookingException;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.BookingRequest;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private UserDao userDao;

    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<BookingDto> findBookings(Long userId) {
        List<Booking> list = bookingDao.findBookings(userId);
        return list.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public BookingInfoDto getBookingInfo(Long id) {
        Optional<MarkDto> markDto = bookingDao.findMarksByPlaceId(id);
        Booking booking = bookingDao.find(id);
        return bookingInfoConverter.convert(booking, markDto.get());
    }

    public List<BookingDto> findByStatus(Long id, Status status) {
        List<Booking> bookingList = bookingDao.findUserBookingsByStatus(id, status);
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
        Place place = placeDao.find(bookingRequest.getPlaceId());

        if (placeDao.countBookingsByPlaceIdAndTime(bookingRequest.getPlaceId(),
                bookingRequest.getTimeStart(),
                bookingRequest.getTimeEnd()) != 0) {
            throw new BookingException(String.format("place %s is occupied", place.getPlaceNumber()));
        }

        Booking booking = new Booking();
        booking.setPlace(place);
        booking.setStatus(Status.ACTIVE);
        booking.setTimeStart(bookingRequest.getTimeStart());
        booking.setTimeEnd(bookingRequest.getTimeEnd());
        booking.setUser(userDao.load(userId));

        return bookingConverter.convert(bookingDao.save(booking));
    }

    @Override
    public BookingDto editBooking(BookingRequest bookingRequest, Long userId, Long bookingId) {
        Place place = placeDao.find(bookingRequest.getPlaceId());

        if (placeDao.countBookingsByPlaceIdAndTime(bookingRequest.getPlaceId(),
                bookingRequest.getTimeStart(),
                bookingRequest.getTimeEnd()) != 0) {
            throw new BookingException(String.format("place %s is occupied", place.getPlaceNumber()));
        }

        Booking booking = bookingDao.load(bookingId);
        booking.setPlace(place);
        booking.setTimeStart(bookingRequest.getTimeStart());
        booking.setTimeEnd(bookingRequest.getTimeEnd());
        booking.setUser(userDao.load(userId));

        return bookingConverter.convert(bookingDao.update(booking));
    }

    @Override
    public BookingDto deleteBooking(Long id) {
        Booking booking = bookingDao.load(id);
        booking.setStatus(Status.CANCELED);

        return bookingConverter.convert(bookingDao.save(booking));
    }
}