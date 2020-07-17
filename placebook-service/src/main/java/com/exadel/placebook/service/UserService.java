package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.filters.AdminUserFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    Optional<UserDto> findById(Long id);

    Optional<UserDto> findByEmail(String email);

    List<AdminUserDto> findUsers(AdminUserFilter adminUserFilter);
}
