package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Office;

import java.util.List;

public interface OfficeDao extends BaseDao<Office> {
    List<Office> countActiveBookingsByPlaceIdAndTime(String city, String country);
}