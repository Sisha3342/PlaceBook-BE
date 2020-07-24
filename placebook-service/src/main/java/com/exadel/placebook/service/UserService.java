package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.*;
import com.exadel.placebook.model.filters.AdminUserFilter;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto findById(Long id);

    Optional<UserDto> findByEmail(String email);

    UserStatusDto getUserStatus();

    AdminUserDto updateUserRole(Long id, RoleDto roleDto);

    List<AdminUserDto> findUsers(AdminUserFilter adminUserFilter);

    List<UserSearchDto> findUsers(String text);

    UserDto addUser(UserDto userDto);
}
