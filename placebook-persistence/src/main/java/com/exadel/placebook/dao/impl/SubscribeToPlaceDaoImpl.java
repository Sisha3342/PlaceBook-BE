package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.SubscribeToPlaceDao;
import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.model.entity.Subscribe;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscribeToPlaceDaoImpl extends BaseDaoImpl<Subscribe> implements SubscribeToPlaceDao {

    @Override
    @SuppressWarnings({"unchecked", "deprecation"})
    public List<Subscribe> freePlaceFromSubscribe() {
        Session session = getSession();
        return session.createQuery("select Subscribe " +
                "from Subscribe s " +
                "join s.place p " +
                "join  " +
                "where u.id = :userId " +
                "and b.status = :activeStatus " +
                "and b.timeStart < current_timestamp() " +
                "and b.timeEnd > current_timestamp()")
                .setResultTransformer(Transformers.aliasToBean(PlaceSearchDto.class))
                .setParameter("userId", userId)
                .setParameter("activeStatus", Status.ACTIVE).list();
    }
}
