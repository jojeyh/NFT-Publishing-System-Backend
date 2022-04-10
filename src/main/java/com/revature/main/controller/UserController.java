package com.revature.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.model.UserJwtDTO;
import com.revature.main.service.JwtService;
import com.revature.main.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @GetMapping(value="/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value="/users")
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

    @GetMapping(value="/users/{id}/images")
    public Iterable<Image> getImagesByUserId(@PathVariable String id) {
        return userService.getImagesByUserId(Long.parseLong(id));
    }

    /*
            Requires authentication

            Request Body:
                {
                    "image": `Base64 encoded byte array`,
                    "author": {
                        "id": `User ID`,
                        "username": `Username`,
                        "ethAddress": `Ethereum account address` (optional for now)
                    },
                    "contractAddress": `Ethereum NFT contract address` (optional)
                }

     */
    @PostMapping(value="/users/{id}/images")
    public Image uploadImage(@PathVariable String id,
                             @RequestHeader(value="Authorization") String bearer,
                             @RequestBody Image image) throws JsonProcessingException, UnauthorizedResponse {
        Long userId = Long.parseLong(id);
        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = this.jwtService.parseJwt(jwt);

        if (!token.getUserId().equals(userId)) {
            throw new UnauthorizedResponse("You may only submit reimbursements for yourself");
        }

        return userService.uploadImage(userId, image);
    }
}
