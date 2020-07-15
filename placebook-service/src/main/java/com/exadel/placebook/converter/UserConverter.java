package com.exadel.placebook.converter;


import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convert(User p) {
        UserDto pd = new UserDto();
        pd.setId(p.getId());
        pd.setName(p.getName());
        pd.setSurname(p.getSurname());
        return pd;
    }

    public User convert(UserDto pd) {
        User p = new User();
        p.setName(pd.getName());
        p.setSurname(pd.getSurname());
        p.setId(pd.getId());
        return p;
    }
}
