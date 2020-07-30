package com.exadel.placebook.service.impl;

import com.exadel.placebook.builder.MailMessageBuilder;
import com.exadel.placebook.converter.PlaceConverter;
import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.SubscribeToPlaceDao;
import com.exadel.placebook.model.dto.PlaceDto;
import com.exadel.placebook.model.dto.PlaceSearchDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.entity.Subscribe;
import com.exadel.placebook.model.security.UserContext;
import com.exadel.placebook.service.PlaceService;
import com.exadel.placebook.service.SendMailService;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private SubscribeToPlaceDao subscribeToPlaceDao;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PlaceConverter placeConverter;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private MailMessageBuilder mailMessageBuilder;

    @Override
    public List<PlaceSearchDto> getPlaceByUserNow(Long userId) {
        List<PlaceSearchDto> place = placeDao.getPlaceByUserNow(userId);
        return place;
    }

    @Override
    public PlaceDto subscribeToPlace(Long placeId) {
        UserContext context = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (context == null) throw new RuntimeException();
        UserDto userDto = context.getUserDto();
        Place place = placeDao.find(placeId);
        Subscribe subscribe = new Subscribe();
        subscribe.setPlace(place);
        subscribe.setUser(userConverter.convert(userDto));
        return placeConverter.convert(subscribeToPlaceDao.save(subscribe).getPlace());
    }

    @Override
    public void subscribeChecker() {
       List<Subscribe> list = subscribeToPlaceDao.freePlaceFromSubscribe();
       if(list.size()!=0) {
           list.forEach(s -> sendMailService.sendEmail(mailMessageBuilder.convert(s)));
           subscribeToPlaceDao.deleteSubscribes(list);
       }
    }
}