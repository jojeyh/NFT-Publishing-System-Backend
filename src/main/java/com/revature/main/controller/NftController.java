package com.revature.main.controller;

import com.revature.main.model.NFT;
import com.revature.main.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NftController {

    @Autowired
    NftService nftService;

    @PostMapping(value="/nfts")
    public NFT createNFT(@RequestBody NFT nft) {
        return nftService.addNFT(nft);
    }
}

