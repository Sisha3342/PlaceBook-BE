package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.PlaceRate;

import java.util.Optional;

public interface MarkDao extends BaseDao<PlaceRate> {
    Optional<PlaceRate> getMarksByBookingId(Long id);
}
