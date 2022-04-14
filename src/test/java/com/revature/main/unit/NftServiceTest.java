package com.revature.main.unit;

import com.revature.main.model.NFT;
import com.revature.main.repository.NftRepository;
import com.revature.main.service.NftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NftServiceTest {
    @Mock
    NftRepository nftRepository;

    @InjectMocks
    NftService nftService;

    @Test
    public void addNFTTest(){
        NFT nft = new NFT();
        when(nftRepository.save(nft)).thenReturn(nft);
        NFT actual = nftService.addNFT(nft);
        assertThat(actual).isEqualTo(nft);
    }
    @Test
    public void getNFTByIdTest(){
        NFT mock = new NFT();
        NFT expected = new NFT();

        when(nftRepository.findById(1L)).thenReturn(Optional.of(mock));
        Optional<NFT> actual= nftService.getNFTById(1L);
        Assertions.assertEquals(Optional.of(expected), actual);
    }
    @Test
    public void deleteNFTByIdTest(){
        when(nftRepository.existsById(2L)).thenReturn(false);
        boolean actual = nftService.deleteNFTById(2L);
        assertThat(actual).isEqualTo(true);
    }
    @Test
    public void updateNFTTest(){
        NFT nft = new NFT();
        when(nftRepository.save(nft)).thenReturn(nft);
        NFT actual = nftService.updateNFT(nft);
        assertThat(actual).isEqualTo(nft);
    }
}
