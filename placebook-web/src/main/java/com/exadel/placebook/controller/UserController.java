package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.filters.AdminUserFilter;
import com.exadel.placebook.service.PageValidation;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<AdminUserDto> getUsers(AdminUserFilter adminUserFilter) throws Exception {
        PageValidation.validate(adminUserFilter);
        return userService.findUsers(adminUserFilter);
    }
}
