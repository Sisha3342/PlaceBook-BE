package com.exadel.placebook.controller;


import com.exadel.placebook.converter.BookingConverter;
import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.dao.BookingDao;
import com.exadel.placebook.model.dto.BookingDto;
import com.exadel.placebook.model.enums.Status;
import com.exadel.placebook.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @GetMapping("/bookings")
    public List<BookingDto> findUserBookingsActive(@RequestParam Long id, @RequestParam Status status) {
        return bookingServiceImpl.findByStatus(id, status);
    }

    @GetMapping("/statistics")
    public Map<String, Integer> statistics(@RequestParam Long id) {
        return bookingServiceImpl.statistics(id);
    }


}
