package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.City;
import com.exadel.placebook.model.entity.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityDao extends CrudRepository<City, Long> {
    Optional<City> findById(Long id);
}