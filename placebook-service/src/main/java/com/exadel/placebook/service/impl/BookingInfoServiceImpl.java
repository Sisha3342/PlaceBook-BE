package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.BookingInfoConverter;
import com.exadel.placebook.dao.*;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.entity.*;
import com.exadel.placebook.service.BookingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BookingInfoServiceImpl implements BookingInfoService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BookingInfoConverter bookingInfoConverter;

    @Override
    public BookingInfoDto findBookingInfo(Long id) {
        Optional<Booking> booking = bookingDao.findById(id);
        return bookingInfoConverter.convert(booking.get());
    }

    public BookingInfoDto findBookingInfoTest(Long id){
        return null;
    }
}
