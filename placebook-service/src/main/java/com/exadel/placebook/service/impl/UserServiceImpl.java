package com.exadel.placebook.service.impl;

import com.exadel.placebook.converter.AdminUserConverter;
import com.exadel.placebook.converter.UserConverter;
import com.exadel.placebook.converter.UserSearchConverter;
import com.exadel.placebook.converter.UserStatusConverter;
import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.model.sorting.AdminUserFilter;
import com.exadel.placebook.model.security.UserContext;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private UserStatusConverter userStatusConverter;

    @Autowired
    private AdminUserConverter adminUserConverter;

    @Autowired
    private UserSearchConverter userSearchConverter;

    @Autowired
    private UserService userService;

    @Override
    public UserDto findById(Long id) {
        User user = userDao.find(id);
        return userConverter.convert(user);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> userOptional = userDao.findByEmail(email);
        return userOptional.map(user -> userConverter.convert(user));
    }

    @Override
    public UserStatusDto getUserStatus() {
        UserContext context = (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (context == null) throw new RuntimeException();
        UserDto userDto = context.getUserDto();
        return userStatusConverter.convert(userDto);
    }

    @Override
    public AdminUserDto updateUserRole(Long id, RoleDto roleDto) {
        User user = userDao.find(id);
        user.setRole(roleDto.getRole());
        return adminUserConverter.convert(userDao.update(user));
    }

    @Override
    public List<AdminUserDto> findUsers(AdminUserFilter adminUserFilter) {

        List<User> users = userDao.findUsers(adminUserFilter, userService.getUserStatus().getId());
        return users.stream().map(adminUserConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<UserSearchDto> findUsers(String text) {
        List<User> users = userDao.findUsers(text);
        return users.stream().map(userSearchConverter::convert).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userConverter.convert(userDto);

        if(user.getHrId() == null) {
            user.setHrId(1L);
            user = userDao.save(user);
            user.setHrId(user.getId());
            return userConverter.convert(userDao.update(user));
        }

        return userConverter.convert(userDao.save(user));
    }
}
