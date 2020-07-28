package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Floor;

import java.util.List;

public interface FloorDao extends BaseDao<Floor> {
    List<String> getFloorsNumbersByOfficeId(Long officeId);
}
