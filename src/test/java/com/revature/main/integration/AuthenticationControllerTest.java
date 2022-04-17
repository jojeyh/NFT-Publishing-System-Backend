package com.revature.main.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.main.model.LoginDTO;
import com.revature.main.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc

public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_loginEndpoint_positive() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setUsername("john_smith");
        dto.setPassword("pass12");
        String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

        User expected = new User();
        expected.setId(1L);
        expected.setUsername("john_smith");
        expected.setPassword("pass12");
        expected.setEthAddress("aox12");

        String expectedJson = (new ObjectMapper()).writeValueAsString(expected);

        this.mockMvc.perform(
                        post("/login").
                                contentType(MediaType.APPLICATION_JSON).
                                content(jsonDto))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJson))
                .andExpect(header().string("token", "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2R0byI6IntcInVzZXJJZFwiOjEsXCJ1c2VybmFtZVwiOlwiam9obl9zbWl0aFwifSJ9.uo4nfO0fc-D_vadTfVeabOL1WG6wAKMTEgRWxnskpZwghBHE3GQ8UYRwTRNDXZZxIBVmBJoQD0OxNXuR_g5n8w"));
    }
    @Test
    public void test_loginEndpoint_invalidCredentials() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setUsername("invalid");
        dto.setPassword("invalid");
        String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(401))
                .andExpect(content().string("Invalid username and/or password"));
    }
}
