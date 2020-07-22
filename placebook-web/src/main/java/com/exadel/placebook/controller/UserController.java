package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.model.filters.AdminUserFilter;
import com.exadel.placebook.service.AdminUserFilterValidator;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserFilterValidator adminUserFilterValidator;

    @GetMapping("/users")
    public List<AdminUserDto> getUsers(AdminUserFilter adminUserFilter) {
        adminUserFilterValidator.validate(adminUserFilter);
        return userService.findUsers(adminUserFilter);
    }

    @PutMapping("/users/{userId}/{role}")
    public int changeUserRole(@PathVariable("userId") Long id, @PathVariable("role") Role role) {
        return userService.updateUserRole(id, role);
    }
}
