package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.model.dto.AdminDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<User> userOptional = userDao.findById(id);
        return userOptional.map(user -> userConverter.convert(user));
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> userOptional = userDao.findByEmail(email);
        return userOptional.map(user -> userConverter.convert(user));
    }

    @Override
    public AdminDto findAllByIdBetweenAndNameContainingAndSurnameContaining(Long offset, Long limit, String name, String surname) {
        List<User> users = userDao.findAllByIdBetweenAndNameContainingAndSurnameContaining(offset, offset + limit - 1, name, surname);
        return userConverter.convert(users);
    }

}
