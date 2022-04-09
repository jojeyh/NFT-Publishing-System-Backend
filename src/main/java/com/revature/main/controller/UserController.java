package com.revature.main.controller;

import com.revature.main.model.User;
import com.revature.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value="/users/{id}")
    public User getUserById(@PathVariable String id){

        try {
            Long num = Long.parseLong(id);
            return userService.getUserById(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User id must be an integer to retrieve an user");
        }
    }
}
