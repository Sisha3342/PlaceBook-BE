package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByEmail(String email);
}
