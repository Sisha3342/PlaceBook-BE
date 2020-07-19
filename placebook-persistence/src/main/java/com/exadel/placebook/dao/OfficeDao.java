package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OfficeDao extends CrudRepository<Office, Long> {
    Optional<Office> findById(Long id);
}