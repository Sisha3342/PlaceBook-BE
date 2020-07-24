package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.converter.BookingInfoConverter;
import com.exadel.placebook.converter.OfficeConverter;
import com.exadel.placebook.dao.AddressDao;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.dao.OfficeDao;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.MarkDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.entity.Office;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.exception.MarksNotFoundException;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AddressDao addressDao;

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private BookingInfoConverter bookingInfoConverter;

    @Autowired
    private OfficeConverter officeConverter;

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

    @Override
    public List<String> getAllCountries() {
        return addressDao.findAllCountries();
    }

    @Override
    public List<String> getAllCitiesByCountry(String country) {
        return addressDao.findAllCitiesByCountry(country);
    }

    @Override
    public List<OfficeDto> getAllOfficesByCity(String city) {
        List<Office> list = officeDao.findAllOfficesByCity(city);
        return list.stream().map(officeConverter::convert).collect(Collectors.toList());
    }

    @Override
    public MarkDto getMarksByPlaceId(Long id) {
        Optional<MarkDto> marks = bookingDao.findMarksByPlaceId(id);
        if(!marks.isPresent()) {
            throw new MarksNotFoundException("marks is not found");
        }
        return marks.get();
    }

    @Override
    public void completeEndedBooking() {
        bookingDao.completeEndedBookings();
    }

    public List<BookingDto> findByStatus(Long id, Status status) {
        List<Booking> bookingList = bookingDao.findUserBookingsByStatus(id, status);
        return bookingList.stream().map(bookingConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Map<Status, Long> getStatistics(Long id) {
        return bookingDao.getStatistics(id);
    }
}