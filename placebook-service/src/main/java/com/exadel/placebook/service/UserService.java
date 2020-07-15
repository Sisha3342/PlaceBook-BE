package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.UserDto;

public interface UserService {

    UserDto findById(Long id);
}
