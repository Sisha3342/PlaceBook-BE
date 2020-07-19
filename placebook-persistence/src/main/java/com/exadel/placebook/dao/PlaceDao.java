package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceDao extends CrudRepository<Place, Long> {
    Optional<Place> findById(Long id);

}

