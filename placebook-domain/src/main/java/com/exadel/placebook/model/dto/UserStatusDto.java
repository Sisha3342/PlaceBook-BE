package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Role;
import lombok.Data;

@Data
public class UserStatusDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private Role role;
}
