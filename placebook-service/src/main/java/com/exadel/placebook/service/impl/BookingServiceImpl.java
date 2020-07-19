package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
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

    @Override
    public List<BookingDto> findBookings(Long userId) {
        List<Booking> list = bookingDao.findBookings(userId);
        return list.stream().map((p) -> bookingConverter.convert(p)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> statistics(Long id) {
        List<BookingDto> list = findBookings(id);
        Map<String, Integer> statistic = new HashMap<>();
        statistic.put("ACTIVE", 0);
        statistic.put("COMPLETED", 0);
        statistic.put("CANCELED", 0);
        list.stream().forEach((p) -> statistic.put(p.getStatus().toString(), statistic.get(p.getStatus().toString()) + 1));
        return statistic;
    }

    @Override
    public List<BookingDto> findByStatus(Long id, Status status) {
        List<Booking> bookingList = bookingDao.findUserBookingsByStatus(id, status);
        return bookingList.stream().map((p) -> bookingConverter.convert(p)).collect(Collectors.toList());
    }
}
