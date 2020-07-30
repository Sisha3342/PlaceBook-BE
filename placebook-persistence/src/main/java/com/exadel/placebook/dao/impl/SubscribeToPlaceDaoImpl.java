package com.exadel.placebook.dao.impl;

import com.exadel.placebook.dao.SubscribeToPlaceDao;
import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.entity.Subscribe;
import com.exadel.placebook.model.enums.Status;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class SubscribeToPlaceDaoImpl extends BaseDaoImpl<Subscribe> implements SubscribeToPlaceDao {

    @Override
    public List<Subscribe> freePlaceFromSubscribe() {
        Session session = getSession();
        return session.createQuery("select s " +
                "from Subscribe s " +
                "join s.place p "+
                "where p.id IN (select b.place.id from Booking b " +
                        "where b.status = 'CANCELED' or b.status = 'COMPLETED')"
                        , Subscribe.class)
                .list();
    }

    @Override
    public void deleteSubscribes(List<Subscribe> list) {
        Session session = getSession();
        Query query = session.createQuery("delete from Subscribe s where "
                + "s IN :list ").setParameter("list", list);
        int result = query.executeUpdate();
    }


}
