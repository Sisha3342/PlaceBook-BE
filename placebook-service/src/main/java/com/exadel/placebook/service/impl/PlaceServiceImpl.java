package com.exadel.placebook.service.impl;

import com.exadel.placebook.builder.MailMessageBuilder;
import com.exadel.placebook.converter.PlaceBlockConverter;
import com.exadel.placebook.converter.PlaceConverter;
import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.dao.PlaceBlockDao;
import com.exadel.placebook.dao.PlaceDao;
import com.exadel.placebook.dao.SubscribeToPlaceDao;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.entity.Place;
import com.exadel.placebook.model.entity.PlaceBlock;
import com.exadel.placebook.model.entity.Subscribe;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.exception.EntityNotFoundException;
import com.exadel.placebook.model.security.UserContext;
import com.exadel.placebook.service.PlaceService;
import com.exadel.placebook.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private static final long BLOCK_TIME_IN_MINUTES = 1;

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

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlaceBlockDao placeBlockDao;

    @Autowired
    private PlaceBlockConverter placeBlockConverter;

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
        List<Subscribe> subscribes = subscribeToPlaceDao.freePlaceFromSubscribe();
        if (subscribes.size() != 0) {
            List<Long> list = new ArrayList<>();
            subscribes.forEach(s -> list.add(s.getId()));
            subscribeToPlaceDao.deleteSubscribes(list);
            subscribes.forEach(s -> sendMailService.sendEmail(mailMessageBuilder.convert(s)));
        }
    }

    @Override
    public PlaceBlockResponse blockPlaceForUser(Long placeId, Long userId) {
        User user = userDao.find(userId);

        if(user == null) {
            throw new EntityNotFoundException(User.class, userId);
        }

        Place place = placeDao.getPlaceWithPlaceBlock(placeId);

        if(place == null) {
            throw new EntityNotFoundException(Place.class, placeId);
        }

        PlaceBlock existing = place.getPlaceBlock();

        if(existing != null) {
            if (existing.getBlockEnd().isBefore(LocalDateTime.now())) {
                placeBlockDao.delete(existing.getId());
            } else {
                return placeBlockConverter.convert(existing);
            }
        }

        PlaceBlock placeBlock = new PlaceBlock();

        placeBlock.setUser(user);
        placeBlock.setPlace(place);
        placeBlock.setBlockEnd(LocalDateTime.now().plusMinutes(BLOCK_TIME_IN_MINUTES));

        return placeBlockConverter.convert(placeBlockDao.save(placeBlock));
    }
}
