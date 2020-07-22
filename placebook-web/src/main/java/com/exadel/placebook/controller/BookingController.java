package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.dto.BookingInfoDto;
import com.exadel.placebook.model.dto.OfficeDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/user/{userId}/bookings/{status}")
    public List<BookingDto> findUserBookingsActive(@PathVariable("userId") Long userId, @PathVariable("status") Status status) {
        return bookingService.findByStatus(userId, status);
    }

    @GetMapping("/user/{userId}/booking/statistics")
    public Map<Status, Long> getStatistics(@PathVariable("userId") Long userId) {
        return bookingService.getStatistics(userId);
    }

    @GetMapping("/user/{userId}/booking/{bookingId}/info")
    public BookingInfoDto findBookingInfo(@PathVariable("userId") Long userId, @PathVariable("bookingId") Long bookingId) {
        return bookingService.getBookingInfo(bookingId);
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        return bookingService.getAllCountries();
    }

    @GetMapping("/countries/{country}/cities")
    public List<String> getAllCities(@PathVariable("country") String country) {
        return bookingService.getAllCitiesByCountry(country);
    }

    @GetMapping("countries/{country}/cities/{city}/offices")
    public List<OfficeDto> getAllOffices(@PathVariable("country") String country, @PathVariable("city") String city) {
        return bookingService.getAllOfficesByCity(city);
    }
}