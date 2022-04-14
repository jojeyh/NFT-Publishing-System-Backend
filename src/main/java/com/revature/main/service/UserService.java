package com.revature.main.service;

import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.repository.ImageRepository;
import com.revature.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // TODO store passwords as hashes, change User to not store passwords

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // TODO Write unit tests
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<Image> getImagesByUserId(Long id) {
        return imageRepository.findImagesByUserId(id);
    }

    public Image uploadImage(Image image) throws UnauthorizedResponse {
        return imageRepository.save(image);
    }

    public Optional<Image> getImageByImageId(Long imageId) {
        return imageRepository.findById(imageId);
    }

    public Boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }
}