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
    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userDao.findById(id);
        return user.map(usr -> userConverter.convert(usr));
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userDao.findByEmail(email);
        return user.map(usr -> userConverter.convert(usr));
    }
}
