package com.revature.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.exception.BadParameterException;
import com.revature.main.model.LoginDTO;
import com.revature.main.model.User;
import com.revature.main.service.AuthenticationService;
import com.revature.main.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) throws JsonProcessingException {
        try {
            User user = authService.login(dto.getUsername(), dto.getPassword());

            // Utilize JwtService to create a JSON web token with user information inside
            String jwt = jwtService.createJwt(user);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("token", jwt);

            return ResponseEntity.ok().headers(responseHeaders).body(user);
        } catch (FailedLoginException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (BadParameterException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
