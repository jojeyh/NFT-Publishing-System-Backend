package com.revature.main.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import com.revature.main.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void positive_isStatusOk() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "patronius", "password", "0xfaceded"));

        mvc.perform(MockMvcRequestBuilders.get("/api/users")).andExpect(status().isOk());
    }

    /*
    @Test
    public void positive_getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "patronius", "password", "0xafeded"));

        given(userService.getAllUsers()).willReturn(users);

        mockMvc.getDispatcherServlet().getHandlerMappings();
        ResultActions result = mockMvc.perform(
                get("/api/users")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("patronius"));
    }
     */

}
