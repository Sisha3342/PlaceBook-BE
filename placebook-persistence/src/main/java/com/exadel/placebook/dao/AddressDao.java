package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Address;

import java.util.List;

public interface AddressDao extends BaseDao<Address> {
    List<String> findAllCountries();
    List<String> findCitiesByCountry(String country);
}