package com.exadel.placebook.service.impl;

import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.exception.MarksNotFoundException;
import com.exadel.placebook.model.exception.PlaceNotFoundException;
import com.exadel.placebook.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Override
    public PlaceDto getPlaceByUserNow(Long userId) {
        Optional<PlaceDto> place = placeDao.getPlaceByUserNow(userId);
        if (!place.isPresent()) {
            throw new PlaceNotFoundException("place is not found");
        }
        return place.get();
    }
}
