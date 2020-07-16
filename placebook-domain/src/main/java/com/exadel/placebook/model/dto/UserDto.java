package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String name;
    private String email;
    private String password;
    private String surname;
    private UserDto hr;
    private Role role;
    private String photoUrl;
}
