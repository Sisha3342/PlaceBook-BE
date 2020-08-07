package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
    private Long hrId;
    @NotBlank
    private String photoUrl;
}