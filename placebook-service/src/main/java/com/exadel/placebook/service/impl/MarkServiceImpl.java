package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.MarkConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.dao.MarkDao;
import com.exadel.placebook.model.dto.MarkParams;
import com.exadel.placebook.model.dto.MarkSubmitDto;
import com.exadel.placebook.model.entity.Booking;
import com.exadel.placebook.model.entity.PlaceRate;
import com.exadel.placebook.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkDao markDao;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private MarkConverter markConverter;

    @Override
    public MarkSubmitDto submitMark(Long id, MarkParams markParams) {
        PlaceRate placeRate = markDao.checkMarksByBookingId(id);
        if (placeRate == null)
            placeRate = new PlaceRate();
        Booking booking = bookingDao.load(id);
        placeRate.setBooking(booking);
        placeRate.setMarkAir(markParams.getMarkAir());
        placeRate.setMarkCleaning(markParams.getMarkCleaning());
        placeRate.setFeedback(markParams.getFeedBack());
        placeRate.setMarkLocation(markParams.getMarkLocation());
        placeRate.setMarkLightning(markParams.getMarkLightning());
        placeRate.setMarkVolume(markParams.getMarkVolume());
        markDao.saveOrUpdate(placeRate);
        return markConverter.convert(placeRate);
    }

    @Override
    public MarkSubmitDto getMarksByBookingId(Long bookingId) {
        return markConverter.convert(markDao.getMarksByBookingId(bookingId).orElse(new PlaceRate()));
    }
}
