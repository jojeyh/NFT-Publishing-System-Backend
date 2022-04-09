package com.revature.main.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.revature.main.model.User;
import com.revature.main.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

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

        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("patronius"))
                .andExpect(jsonPath("$[0].password").value("password"));
    }

    /*

        Positive test:
        IllegalArgumentException on the controller layer for getUserById

     */
    @Test
    public void positive_getUserById() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("Kratos");
        user.setPassword("pass123");
        user.setEthAddress("aox123123");

        when(this.userService.getUserById(1L)).thenReturn(user);

        mvc.perform(get("/users/{id}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Kratos"))
                .andExpect(jsonPath("$.password)").value("pass123"));

    }
}
