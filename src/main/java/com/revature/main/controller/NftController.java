package com.revature.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.main.exception.UnauthorizedResponse;
import com.revature.main.model.NFT;
import com.revature.main.model.UserJwtDTO;
import com.revature.main.service.JwtService;
import com.revature.main.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class NftController {

    @Autowired
    NftService nftService;

    @Autowired
    JwtService jwtService;

    @PostMapping(value="/nfts")
    public NFT createNFT(@RequestBody NFT nft,
                         @RequestHeader(value="Authorization") String bearer)
            throws UnauthorizedResponse, JsonProcessingException {
        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = jwtService.parseJwt(jwt);

        if (token.getUserId().equals(nft.getImage().getAuthor().getId())) {
            return nftService.addNFT(nft);
        } else {
            throw new UnauthorizedResponse("You must have authorization to delete this account");
        }
    }

    @GetMapping(value="/nfts/{id}")
    public Optional<NFT> getNFTById(@PathVariable String id) {
        return nftService.getNFTById(Long.parseLong(id));
    }

    @DeleteMapping(value="/nfts/{id}")
    public Boolean deleteNFTById(@PathVariable String id) {
        return nftService.deleteNFTById(Long.parseLong(id));
    }

    // TODO Include authorization logic for changing ownershi
    /*
        Requires fully-formed nft object to be patched
     */
    @PatchMapping(value="/nfts/{id}")
    public NFT updateNFT(@PathVariable String id,
                         @RequestBody NFT nft,
                         @RequestHeader(value="Authorization") String bearer) throws UnauthorizedResponse, JsonProcessingException {

        /*
        NFT originalNft = nftService.getNFTById(Long.parseLong(id)).get();

        String jwt = bearer.split(" ")[1];

        UserJwtDTO token = jwtService.parseJwt(jwt);

        if (token.getUserId().equals(originalNft.getAuthor().getId())) {
            return nftService.updateNFT(nft);
        } else {
            throw new UnauthorizedResponse("You must have authorization to update this nft");
        }
         */
        return nftService.updateNFT(nft);
    }
}

