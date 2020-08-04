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
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscribeToPlaceDaoImpl extends BaseDaoImpl<Subscribe> implements SubscribeToPlaceDao {

    @Override
    public List<Subscribe> freePlaceFromSubscribe() {
        Session session = getSession();
        List<Status> status = new ArrayList<>();
        status.add(Status.CANCELED);
        status.add(Status.COMPLETED);
        return session.createQuery("select s " +
                "from Subscribe s " +
                "join Booking b on b.place.id = s.place.id and b.user.id =s.user.id "+
                "where b.status IN :status"
                        , Subscribe.class)
                .setParameter("status", status)
                .list();
    }

    @Override
    public void deleteSubscribes(List<Long> list) {
        Session session = getSession();
        Query query = session.createQuery("delete from Subscribe s where "
                + "s.id IN :list ").setParameter("list", list);
        int result = query.executeUpdate();
    }


}
