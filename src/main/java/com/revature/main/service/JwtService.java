package com.revature.main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.main.model.User;
import com.revature.main.model.UserJwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

// TODO persist JWT for a specific user for 30 days

@Service
public class JwtService {

    private Key key;

    public JwtService() {
        byte[] secret = "my_secret_passwordafdsalkj;lkvjasd;la;giha;wegha;diga;oieha;ghs;lkjgasdfsdejfDSFSLDKJFLSKJF".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    public String createJwt(User user) throws JsonProcessingException {
        UserJwtDTO dto = new UserJwtDTO(user.getId(), user.getUsername());

        String jwt = Jwts.builder()
                .claim("user_dto", new ObjectMapper().writeValueAsString(dto))
                .signWith(key)
                .compact();

        return jwt;
    }

    public UserJwtDTO parseJwt(String jwt) throws JsonProcessingException {
        Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

        String dtoString = (String) token.getBody().get("user_dto");

        return (new ObjectMapper()).readValue(dtoString, UserJwtDTO.class);
    }
}
