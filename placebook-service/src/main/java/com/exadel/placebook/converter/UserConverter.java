package com.exadel.placebook.converter;


import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convert(User p) {
        UserDto pd = new UserDto();
        pd.setId(p.getId());
        pd.setEmail(p.getEmail());
        pd.setPassword(p.getPassword());
        pd.setName(p.getName());
        pd.setSurname(p.getSurname());
        pd.setHrId(p.getHrId());
        pd.setRole(p.getRole());
        pd.setPhotoUrl(p.getPhotoUrl());
        return pd;
    }

    public User convert(UserDto pd) {
        User p = new User();
        p.setId(pd.getId());
        p.setEmail(pd.getEmail());
        p.setPassword(new BCryptPasswordEncoder().encode(pd.getPassword()));
        p.setName(pd.getName());
        p.setSurname(pd.getSurname());
        p.setHrId(pd.getHrId());
        p.setRole(pd.getRole());
        p.setPhotoUrl(pd.getPhotoUrl());
        return p;
    }


}
