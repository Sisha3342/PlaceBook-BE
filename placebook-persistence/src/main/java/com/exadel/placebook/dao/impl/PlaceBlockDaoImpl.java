package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.PlaceBlockDao;
import com.exadel.placebook.model.entity.PlaceBlock;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceBlockDaoImpl extends BaseDaoImpl<PlaceBlock> implements PlaceBlockDao {

    @Override
    public boolean deleteByUserId(Long userId) {
        return getSession().createQuery("delete from PlaceBlock where user.id = :id")
                .setParameter("id", userId)
                .executeUpdate() != 0;
    }
}
