package com.revature.main.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.main.controller.NftController;
import com.revature.main.model.Image;
import com.revature.main.model.LoginDTO;
import com.revature.main.model.NFT;
import com.revature.main.model.User;
import com.revature.main.service.NftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*@SpringBootTest
@AutoConfigureMockMvc
public class NftControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NftService nftService;

    @MockBean
    private NftController nftController;

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void createNftTest() throws Exception {
        User user = new User(2L, "jane_smith", "pass", "aox11");
        Image image = new Image(2L, "url2", user);
        NFT nft = new NFT(2L, "sym", "aox22", "Monkey", "id1", "own", "uri", image);
        HttpEntity<NFT> entity = new HttpEntity<NFT>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/nfts"),
                HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(actual.contains("/nfts"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
