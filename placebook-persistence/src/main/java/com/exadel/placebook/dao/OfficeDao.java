package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OfficeDao {
    Office findById(Long id);
    Office update(Office office);
    Office save(Office office);
}