package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.FloorDao;
import com.exadel.placebook.model.entity.Floor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FloorDaoImpl extends BaseDaoImpl<Floor> implements FloorDao {

    @Override
    public List<String> getFloorsNumbersByOfficeId(Long officeId) {
        return getSession()
                .createQuery("select f.floorNumber from Floor f where f.office.id = :id and f.deleted = false", String.class)
                .setParameter("id", officeId)
                .list();
    }
}
