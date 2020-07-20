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
    private UserDao userDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private FloorsDao floorsDao;

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private BookingInfoConverter bookingInfoConverter;

    @Override
    public BookingInfoDto findBookingInfo(Long id) {
        Optional<Booking> booking = bookingDao.findById(id);
        Optional<User> user = userDao.findById(booking.get().getUserId());
        Optional<Place> place = placeDao.findById(booking.get().getPlaceId());
        Optional<Floors> floors = floorsDao.findById(place.get().getFloorId());
        Optional<Office> office = officeDao.findById(floors.get().getOfficeId());
        Optional<Country> country = countryDao.findById(office.get().getCountryId());
        Optional<City> city = cityDao.findById(office.get().getCityId());
        Optional<Address> address = addressDao.findById(office.get().getAddressId());
        return bookingInfoConverter.convert(booking.get(), user.get(), place.get(), country.get(), city.get(), address.get());
    }

    public BookingInfoDto findBookingInfoTest(Long id){
        return null;
    }
}
