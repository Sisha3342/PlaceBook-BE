package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Floors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorsDao extends CrudRepository<Floors, Long> {
    Optional<Floors> findById(Long id);
}
