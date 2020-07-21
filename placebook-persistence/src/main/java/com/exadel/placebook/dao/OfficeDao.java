package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Office;

public interface OfficeDao extends BaseDao<Office>{
    Office findById(Long id);
    Office update(Office office);
    Office save(Office office);
}