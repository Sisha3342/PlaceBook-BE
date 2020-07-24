package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.dto.RoleDto;
import com.exadel.placebook.model.enums.Role;
import com.exadel.placebook.model.filters.AdminUserFilter;
import com.exadel.placebook.service.AdminUserFilterValidator;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/users/{userId}")
    public AdminUserDto changeUserRole(@PathVariable("userId") Long id, @RequestBody RoleDto role) {
        return userService.updateUserRole(id, role);
    }

    @PutMapping("/user")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("/users/search")
    public List<UserSearchDto> getUsersBySearch(@RequestParam("text") String searchText){
        return userService.findUsers(searchText);
    }
}
