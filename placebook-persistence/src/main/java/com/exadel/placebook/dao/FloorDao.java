package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorDao extends CrudRepository<Floor, Long> {

    Optional<Floor> findById(Long id);
}
