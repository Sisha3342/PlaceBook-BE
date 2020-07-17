package com.exadel.placebook.converter;


import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convert(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

    public User convert(UserDto userDto) {
        return new ModelMapper().map(userDto, User.class);
    }
}
