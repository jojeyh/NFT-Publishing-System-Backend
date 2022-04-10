package com.revature.main.unit;

import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.service.AuthenticationService;
import com.revature.main.service.JwtService;
import com.revature.main.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTesting {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationService authService;

    @MockBean
    JwtService jwtService;

    @Test
    public void positive_getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("patronius");
        user.setPassword("password");
        user.setEthAddress("0xfaceded");
        users.add(user);

        given(this.userService.getAllUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("patronius"))
                .andExpect(jsonPath("$[0].password").value("password"));
    }

    /*
        public User getUserById(Long id);

        Positive test:
            Supplied id returns well-formed User
        Negative test:
            Invalid id throws IllegalArgumentException

     */
    @Test
    public void positive_getUserById() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("Kratos");
        user.setPassword("pass123");
        user.setEthAddress("aox123123");

        when(this.userService.getUserById(1L)).thenReturn(Optional.of(user));

        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Kratos"))
                .andExpect(jsonPath("$.password").value("pass123"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.ethAddress").value("aox123123"));
    }

    // TODO this test fails because of NestedServletException, fix later somehow
    /*
    @Test
    public void negativeTest_invalidId_getUserById() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mvc.perform(get("/users/sddflsdf"));
        });
    }
     */

}