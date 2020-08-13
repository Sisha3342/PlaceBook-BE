package com.exadel.placebook.controller;

import com.exadel.placebook.model.dto.UserStatusDto;
import com.exadel.placebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStatusController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/status")
    public UserStatusDto getUserStatus() {
        return userService.getUserStatus();
    }
}
