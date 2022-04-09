package com.revature.main.service;

import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // TODO Write unit tests
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // /api/users/{user_id}
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
