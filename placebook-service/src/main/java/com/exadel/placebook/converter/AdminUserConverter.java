package com.exadel.placebook.converter;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.entity.User;
import org.springframework.stereotype.Component;


@Component
public class AdminUserConverter {
    public AdminUserDto convert(User user) {
        AdminUserDto aud = new AdminUserDto();
        aud.setId(user.getId());
        aud.setEmail(user.getEmail());
        aud.setName(user.getName());
        aud.setSurname(user.getSurname());
        aud.setRole(user.getRole());
        aud.setPhotoUrl(user.getPhotoUrl());
        return aud;
    }
}
