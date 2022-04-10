package com.revature.main.service;

import com.revature.main.model.NFT;
import com.revature.main.repository.NftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NftService {

    @Autowired
    NftRepository nftRepository;

    /*
        Requires authentication


     */
    public NFT addNFT(NFT nft) {

        return nftRepository.save(nft);
    }
}
