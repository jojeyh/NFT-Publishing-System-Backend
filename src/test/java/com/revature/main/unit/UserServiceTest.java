package com.revature.main.unit;

import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.exception.UserNotFoundException;
import com.revature.main.model.Image;
import com.revature.main.model.NFT;
import com.revature.main.model.User;
import com.revature.main.repository.ImageRepository;
import com.revature.main.repository.UserRepository;
import com.revature.main.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    ImageRepository imageRepository;

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

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> userList = (List<User>) userService.getAllUsers();
        assertThat(userList).isNotNull();
    }

    @Test
    public void createUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("patronius");
        user.setPassword("password");
        user.setEthAddress("0xaaaa");

        when(userRepository.save(user)).thenReturn(user);
        User actual = userService.createUser(user);
        assertThat(actual).isEqualTo(user);
    }

    @Test
    public void testDeleteUserById() throws UserNotFoundException {
        when(userRepository.existsById(2L)).thenReturn(false);
        boolean actual = userService.deleteUserById(2L);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void uploadImageTest() throws UnauthorizedResponse {
        Image image = new Image();
        when(imageRepository.save(image)).thenReturn(image);
        Image actual = userService.uploadImage(image);
        assertThat(actual).isEqualTo(image);
    }

    @Test
    public void getImageByImageIdTest(){
        Image mock = new Image();
        Image expected = new Image();

        when(imageRepository.findById(1L)).thenReturn(Optional.of(mock));
        Optional<Image> actual= userService.getImageByImageId(1L);
        Assertions.assertEquals(Optional.of(expected), actual);
    }

   @Test
    public void getImagesByUserIdTest() {
        Image image = new Image();
        List<Image> allImageList = new ArrayList<>();
        allImageList.add(image);
        when(imageRepository.findImagesByUserId(1L)).thenReturn(allImageList);
        Iterable<Image> actual = userService.getImagesByUserId(1L);

     assertThat(actual).isNotNull();
    }
}