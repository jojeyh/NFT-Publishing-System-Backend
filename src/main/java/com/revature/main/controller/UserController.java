package com.revature.main.controller;

import com.revature.main.model.User;
import com.revature.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value="/users/{id}")
    public Optional<User> getUserById(@PathVariable String id) {

        try {
            Long num = Long.parseLong(id);
            return userService.getUserById(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User id must be an integer to retrieve an user");
        }

    }
}
