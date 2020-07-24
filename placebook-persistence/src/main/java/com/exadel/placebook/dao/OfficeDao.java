package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Floor;
import com.exadel.placebook.model.entity.Office;

import java.util.List;

public interface OfficeDao extends BaseDao<Office>{
    List<Office> findAllOfficesByCity(String city);
    List<Floor> findFloorsByOfficeId(Long officeId);
}