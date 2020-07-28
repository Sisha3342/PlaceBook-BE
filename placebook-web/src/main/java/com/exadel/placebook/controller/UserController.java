package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.AdminUserDto;
import com.exadel.placebook.model.dto.RoleDto;
import com.exadel.placebook.model.dto.UserDto;
import com.exadel.placebook.model.dto.UserSearchDto;
import com.exadel.placebook.model.exception.ValidationException;
import com.exadel.placebook.model.filters.AdminUserFilter;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<AdminUserDto> getUsers(@RequestParam("limit") int limit,
                                       @RequestParam("offset") int offset,
                                       @RequestParam("text") String text/*,
                                       BindingResult result*/) {

        AdminUserFilter adminUserFilter = new AdminUserFilter(limit, offset, text);

        /*if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }*/

        return userService.findUsers(adminUserFilter);
    }

    @PutMapping("/users/{userId}")
    public AdminUserDto changeUserRole(@PathVariable("userId") Long id, @Valid @RequestBody RoleDto role,
                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        return userService.updateUserRole(id, role);
    }

    @PostMapping("/user")
    public UserDto addUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().toString());
        }
        return userService.addUser(userDto);
    }

    @GetMapping("/users/search")
    public List<UserSearchDto> getUsersBySearch(@RequestParam("text") String searchText) {
        return userService.findUsers(searchText);
    }
}
