package com.revature.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.model.NFT;
import com.revature.main.model.UserJwtDTO;
import com.revature.main.service.JwtService;
import com.revature.main.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NftController {

    @Autowired
    NftService nftService;

    @Autowired
    JwtService jwtService;

    @PostMapping(value="/nfts")
    public NFT createNFT(@RequestBody NFT nft,
                         @RequestHeader String bearer) throws UnauthorizedResponse, JsonProcessingException {
        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = jwtService.parseJwt(jwt);

        if (token.getUserId().equals(nft.getAuthor().getId())) {
            return nftService.addNFT(nft);
        } else {
            throw new UnauthorizedResponse("You must have authorization to delete this account");
        }
    }
}

