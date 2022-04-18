package com.revature.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.model.UserJwtDTO;
import com.revature.main.service.JwtService;
import com.revature.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
@RestController
public class UserController {
    // TODO Add a PATCH endpoint for images, also create more robust patch logic for both images and nfts
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

    @GetMapping(value="/users/{userId}/images/{imageId}")
    public Optional<Image> getImageByImageId(@PathVariable String userId, @PathVariable String imageId) {
        return userService.getImageByImageId(Long.parseLong(imageId));
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
                             @RequestBody Image image) throws UnauthorizedResponse, JsonProcessingException {
        Long userId = Long.parseLong(id);
        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = this.jwtService.parseJwt(jwt);

        if (!token.getUserId().equals(userId)) {
            throw new UnauthorizedResponse("You may only upload images for yourself");
        }

        return userService.uploadImage(image);
    }

    /*
        Requires authorization

        If user is deleted returns `true` otherwise returns `false`

        Note: User deletion will CASCADE DELETE all linked rows in table 'nps_images'
     */
    @DeleteMapping(value="/users/{id}")
    public Boolean deleteUserById(@PathVariable String id,
                                  @RequestHeader(value="Authorization") String bearer)
            throws JsonProcessingException, UnauthorizedResponse {
        Long userId = Long.parseLong(id);
        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = jwtService.parseJwt(jwt);

        if (!token.getUserId().equals(id)) {
            return userService.deleteUserById(userId);
        } else {
            throw new UnauthorizedResponse("You must have authorization to delete this account");
        }
    }
}
