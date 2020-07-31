package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Office;

import java.util.List;

public interface OfficeDao extends BaseDao<Office> {
    List<Office> findOfficesByCityAndCountry(String city, String country);
    List<Floor> findFloorsByOfficeId(Long officeId);
    Office findOfficeById(Long id);
}