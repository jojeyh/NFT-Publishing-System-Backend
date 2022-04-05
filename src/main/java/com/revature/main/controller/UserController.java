package com.revature.main.controller;

import com.revature.main.model.User;
import com.revature.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
