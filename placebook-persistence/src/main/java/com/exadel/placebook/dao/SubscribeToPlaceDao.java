package com.exadel.placebook.dao;

import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.entity.Subscribe;

import java.util.List;

public interface SubscribeToPlaceDao extends BaseDao<Subscribe> {
    List<Subscribe> freePlaceFromSubscribe();
    void deleteSubscribes(List<Subscribe> list);
}
