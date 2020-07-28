package com.exadel.placebook.model.dto;

import com.exadel.placebook.model.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleDto {
    @NotNull
    private Role role;
}
