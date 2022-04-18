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
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NftControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MockMvc mockMvc;


@Test
    public void getNftByIdTest() throws Exception{
    ResponseEntity<NFT> nft = testRestTemplate.getForEntity("/nfts/1", NFT.class);

    assertEquals(1, nft.getBody().getId());
    assertEquals("dsa", nft.getBody().getContractAddress());
    assertEquals("nefs", nft.getBody().getName());
    assertEquals(1L, nft.getBody().getOwner());
    assertEquals("nfs", nft.getBody().getSymbol());
    assertEquals("id1", nft.getBody().getTokenId());
    assertEquals("asd", nft.getBody().getTokenUri());
}



@Test
    public void createNFTTest() throws Exception{

    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:"+randomServerPort+"/nfts";
    URI uri = new URI(baseUrl);


    User user = new User(1L, "john_smith", "pass12", "aox12");
    Image image = new Image(1L, "url12", user);
    NFT nft = new NFT(2L, "asd", "asdasd", "asdd", "ddds", 2L , "asd", image);

    HttpHeaders headers = new HttpHeaders();

    headers.set("Authorization",  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2R0byI6IntcInVzZXJJZFwiOjEsXCJ1c2VybmFtZVwiOlwiam9obl9zbWl0aFwifSJ9.uo4nfO0fc-D_vadTfVeabOL1WG6wAKMTEgRWxnskpZwghBHE3GQ8UYRwTRNDXZZxIBVmBJoQD0OxNXuR_g5n8w");

    HttpEntity<NFT> request = new HttpEntity<>(nft, headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

    assertEquals(200, result.getStatusCodeValue());
    }

@Test
    public void deleteNFTTest() throws Exception{

    mockMvc.perform(MockMvcRequestBuilders.delete("/nfts/{id}", 1L)).andExpect(status().isOk());
    }
}
