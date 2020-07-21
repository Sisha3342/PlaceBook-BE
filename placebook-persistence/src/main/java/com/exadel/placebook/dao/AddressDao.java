package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressDao extends CrudRepository<Address, Long> {

    Optional<Address> findById(Long id);

    List<Address> findAll();
}