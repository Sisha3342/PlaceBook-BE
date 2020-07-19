package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryDao extends CrudRepository<Country, Long> {
    Optional<Country> findById(Long id);
}