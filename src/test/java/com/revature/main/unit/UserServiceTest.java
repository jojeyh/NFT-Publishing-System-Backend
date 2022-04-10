package com.revature.main.unit;

import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import com.revature.main.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    // TODO Write some negative tests
    /*
        User getUserById(Long id);

        Positive Test:
            - Given a valid Id, return User object
     */

    @Test
    public void positiveTest_getUserById() {

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("patronius");
        mockUser.setPassword("password");
        mockUser.setEthAddress("0xaaaa");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        Optional<User> actual = userService.getUserById(1L);

        User expected = new User();
        expected.setId(1L);
        expected.setUsername("patronius");
        expected.setPassword("password");
        expected.setEthAddress("0xaaaa");

        Assertions.assertEquals(Optional.of(expected), actual);
    }

    /*
        public Iterable<Image> getImagesByUserId(Long id)

        Positive Test:
            - Given valid id return well-formed Image list

     */
    @Test
    public void positiveTest_getImagesByUserId() {
        List<Image> images = new ArrayList<>();
        Image mockImage = new Image();
    }
}