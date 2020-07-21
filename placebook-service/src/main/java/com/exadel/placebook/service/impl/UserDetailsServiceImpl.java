package com.exadel.placebook.service.impl;

import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.security.UserContext;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserDto> userDto = userService.findByEmail(email);

        if(!userDto.isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserContext(userDto.get());
    }

}
