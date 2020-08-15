package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.PlaceBlock;

public interface PlaceBlockDao extends BaseDao<PlaceBlock> {
    boolean deleteByUserId(Long userId);
}
