package com.revature.main.integration;

import com.revature.main.model.Image;
import com.revature.main.model.NFT;

import com.revature.main.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NftControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

@Test
    public void getNftByIdTest() throws Exception{
    ResponseEntity<NFT> nft = testRestTemplate.getForEntity("/nfts/1", NFT.class);

    assertEquals(1, nft.getBody().getId());
    assertEquals("dsa", nft.getBody().getContractAddress());
    assertEquals("nefs", nft.getBody().getName());
    assertEquals("juan", nft.getBody().getOwner());
    assertEquals("nfs", nft.getBody().getSymbol());
    assertEquals("id1", nft.getBody().getTokenId());
    assertEquals("asd", nft.getBody().getTokenUri());
}



/*@Test
    public void createNFTTest() throws Exception{

    User user = new User(2L, "jane_smith", "pass", "aox11");
    Image image = new Image(2L, "url2", user);
    NFT nft = new NFT(2L, "sym", "aox22", "Monkey", "id1", "john", "uri", image);

    HttpEntity<NFT> request = new HttpEntity<>(nft);

    ResponseEntity<NFT> response = testRestTemplate.postForEntity("/nfts", request, NFT.class);

    assertEquals(2, response.getBody().getId());
    assertEquals("aox22", response.getBody().getContractAddress());
    assertEquals("Monkey", response.getBody().getName());
    assertEquals("john", response.getBody().getOwner());
    assertEquals("sym", response.getBody().getSymbol());
    assertEquals("id1", response.getBody().getTokenId());
    assertEquals("uri", response.getBody().getTokenUri());
}
*/
    

}
