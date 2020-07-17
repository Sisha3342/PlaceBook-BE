package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.AdminUserConverter;
import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.filters.AdminUserFilter;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AdminUserConverter adminUserConverter;

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
    public List<AdminUserDto> findUsers(AdminUserFilter adminUserFilter) {
        List<User> users = userDao.findUsers(adminUserFilter);
        return users.stream().map((s) -> adminUserConverter.convert(s)).collect(Collectors.toList());
    }
}