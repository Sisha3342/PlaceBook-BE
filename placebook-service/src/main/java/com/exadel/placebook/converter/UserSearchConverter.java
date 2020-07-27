package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.UserSearchDto;
import com.exadel.placebook.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserSearchConverter {
    public UserSearchDto convert(User user) {
        UserSearchDto userSearchDto = new UserSearchDto();
        userSearchDto.setId(user.getId());
        userSearchDto.setName(user.getName());
        userSearchDto.setSurname(user.getSurname());
        userSearchDto.setPhotoUrl(user.getPhotoUrl());
        return userSearchDto;
    }
}
