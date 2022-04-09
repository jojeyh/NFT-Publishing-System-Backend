package com.revature.main.service;

import com.revature.main.exception.BadParameterException;
import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public User login(String username, String password) throws FailedLoginException, BadParameterException {

        if (username.trim() == "" || password.trim() == "") {
            throw new BadParameterException("Blank username/password entered");
        }

        User user = userRepository.findByUsernameAndPassword(username.trim(), password.trim());

        if (user == null) {
            throw new FailedLoginException("Invalid username and/or password");
        }

        return user;
    }
}
