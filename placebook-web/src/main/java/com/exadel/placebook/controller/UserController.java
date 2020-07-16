package com.exadel.placebook.controller;

import com.exadel.placebook.dao.UserDao;
import com.exadel.placebook.model.entity.User;
import com.exadel.placebook.service.PageValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/users", params = {"limit", "offset", "name", "surname"})
    public List<User> getUsers(@RequestParam long limit, long offset, String name, String surname) {
        if (PageValidation.validation(offset, limit)) {
            return userDao.findAllByIdBetweenAndNameContainingAndSurnameContaining(offset, offset + limit - 1, name, surname);
        } else return null;
    }
}
