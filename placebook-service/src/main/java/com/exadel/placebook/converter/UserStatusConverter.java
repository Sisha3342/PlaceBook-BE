package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserStatusConverter {

    public UserStatusDto convert(UserDto pd) {
        UserStatusDto p = new UserStatusDto();
        p.setId(pd.getId());
        p.setEmail(pd.getEmail());
        p.setName(pd.getName());
        p.setSurname(pd.getSurname());
        p.setRole(pd.getRole());
        p.setPhotoUrl(pd.getPhotoUrl());
        return p;
    }
    public UserStatusDto convert(User pd) {
        UserStatusDto p = new UserStatusDto();
        p.setId(pd.getId());
        p.setEmail(pd.getEmail());
        p.setName(pd.getName());
        p.setSurname(pd.getSurname());
        p.setRole(pd.getRole());
        p.setPhotoUrl(pd.getPhotoUrl());
        return p;
    }
}
