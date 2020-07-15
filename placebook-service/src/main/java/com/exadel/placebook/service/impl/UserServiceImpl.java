package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto findById(Long id) {
        Optional<User> p = userDao.findById(id);
        return p.map(user -> userConverter.convert(user)).orElse(null);
    }
}
