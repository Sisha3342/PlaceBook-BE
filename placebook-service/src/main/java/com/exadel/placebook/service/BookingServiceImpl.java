package com.exadel.placebook.service;

import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.exadel.placebook.model.enums.Status.*;

@Service
@Component

public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BookingConverter bookingConverter;

    @Override
    public List<BookingDto> findByStatus(Long id, Status status) {
        List<Booking> bookingList = bookingDao.findBookingsByUserIdAndStatus(id, status);
        return bookingConverter.convert(bookingList);
    }

    @Override
    public Map<String, Integer> statistics(Long id) {
       Map<String, Integer> statistic=new HashMap<>();
        statistic.put("ACTIVE", bookingDao.findBookingsByUserIdAndStatus(id, ACTIVE).size());
        statistic.put("COMPLETED", bookingDao.findBookingsByUserIdAndStatus(id, COMPLETED).size());
        statistic.put("CANCELLED", bookingDao.findBookingsByUserIdAndStatus(id, CANCELED).size());
        return (HashMap<String, Integer>) statistic;
    }
}
