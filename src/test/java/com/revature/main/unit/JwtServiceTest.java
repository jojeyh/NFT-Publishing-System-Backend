package com.revature.main.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.model.User;
import com.revature.main.repository.NftRepository;
import com.revature.main.repository.UserRepository;
import com.revature.main.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    JwtService jwtService;

    @Test
    public void testCreateValidJwt() throws JsonProcessingException {
        String jwt = jwtService.createJwt(new User(1L, "admin", "pass", "aox1010"));
        Assertions.assertEquals(178, jwt.length());
    }

    @Test
    public void testCreatesDifferentJwtsForDifferentUsers() throws JsonProcessingException {

        String jwt1 = jwtService.createJwt(new User(1L, "admin", "pass", "aox1010"));
        String jwt2 = jwtService.createJwt(new User(2L, "admin", "pass", "aox1010"));


        Assertions.assertNotEquals(jwt1, jwt2);
    }

    /*
    @Test
    public void testParseValidJWT() throws JsonProcessingException {
        User user = new User(1L, "admin", "pass", "aox1010");
        String jwt = jwtService.createJwt(user);

        Jws<Claims> token = jwtService.parseJwt(jwt);

        String username = token.getBody().getSubject();
        Object id = token.getBody().get("user_dto");

        Assertions.assertEquals(user.getId(), id);
    }
*/

}
