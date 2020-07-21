package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Office;

import java.util.List;

public interface OfficeDao extends BaseDao<Office>{
    Office findById(Long id);
    Office update(Office office);
    Office save(Office office);
    List<Office> findAllOfficesByCity(String city);
}