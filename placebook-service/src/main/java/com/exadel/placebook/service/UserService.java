package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.model.filters.AdminUserFilter;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto findById(Long id);

    Optional<UserDto> findByEmail(String email);

    UserStatusDto getUserStatus();

    int updateUserRole(Long id, Role role);

    List<AdminUserDto> findUsers(AdminUserFilter adminUserFilter);
}
