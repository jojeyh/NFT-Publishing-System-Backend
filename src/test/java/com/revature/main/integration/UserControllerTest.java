package com.revature.main.integration;

import com.revature.main.model.Image;
import com.revature.main.model.NFT;
import com.revature.main.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }
    @Test
    public void createUser() throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/";
        URI uri = new URI(baseUrl);
        User user = new User();

        ResponseEntity<String> result = restTemplate.postForEntity(uri, user, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    public void getUserByIdTest() throws Exception{
        ResponseEntity<User> user = testRestTemplate.getForEntity("/users/1", User.class);

        assertEquals(1, user.getBody().getId());
        assertEquals("john_smith", user.getBody().getUsername());
        assertEquals("pass12", user.getBody().getPassword());
        assertEquals("aox12", user.getBody().getEthAddress());
    }
    @Test
    public void getImageByUserId() throws Exception{
        ResponseEntity<Image> image = testRestTemplate.getForEntity("/users/1/images/1", Image.class);
        assertEquals(1, image.getBody().getId());
        assertEquals("url12", image.getBody().getImageURL());
    }
    @Test
    public void uploadImage() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/1/images";
        URI uri = new URI(baseUrl);

        User user = new User(1L, "john_smith", "pass12", "aox12");
        Image image = new Image(1L, "url12", user);

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization",  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2R0byI6IntcInVzZXJJZFwiOjEsXCJ1c2VybmFtZVwiOlwiam9obl9zbWl0aFwifSJ9.uo4nfO0fc-D_vadTfVeabOL1WG6wAKMTEgRWxnskpZwghBHE3GQ8UYRwTRNDXZZxIBVmBJoQD0OxNXuR_g5n8w");

        HttpEntity<Image> request = new HttpEntity<>(image, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }
    
}


