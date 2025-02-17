package com.exadel.placebook.model.dto;


import com.exadel.placebook.model.enums.Role;
import lombok.Data;

@Data
public class AdminUserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private String photoUrl;
}
