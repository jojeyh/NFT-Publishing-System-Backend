package com.revature.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nps_nfts")
@NoArgsConstructor @Getter @Setter @ToString
public class NFT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nft_symbol")
    private String symbol;

    @Column(name = "nft_contract_address")
    private String contractAddress;

    @Column(name = "nft_name")
    private String name;

    @Column(name = "nft_token_id")
    private String tokenId;

    @Column(name = "nft_owner")
    private String owner;

    @Column(name = "nft_token_uri")
    private String tokenUri;

    @ManyToOne
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NFT)) return false;
        NFT nft = (NFT) o;
        return id.equals(nft.id) && Objects.equals(symbol, nft.symbol) && Objects.equals(contractAddress, nft.contractAddress) && Objects.equals(name, nft.name) && tokenId.equals(nft.tokenId) && owner.equals(nft.owner) && tokenUri.equals(nft.tokenUri) && author.equals(nft.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, contractAddress, name, tokenId, owner, tokenUri, author);
    }
}
