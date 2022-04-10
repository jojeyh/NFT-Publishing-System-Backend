package com.revature.main.service;

import com.revature.main.model.NFT;
import com.revature.main.repository.NftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<NFT> getNFTById(Long id) {
        return nftRepository.findById(id);
    }

    public Boolean deleteNFTById(Long id) {
        nftRepository.deleteById(id);

        return !nftRepository.existsById(id);
    }

    public NFT updateNFT(NFT nft) {
        return nftRepository.save(nft);
    }
}
