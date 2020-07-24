package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Long hrId;
    private String photoUrl;
}