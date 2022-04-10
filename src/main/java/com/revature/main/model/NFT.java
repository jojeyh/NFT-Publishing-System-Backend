package com.revature.main.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "nps_nfts")
@NoArgsConstructor @Getter @Setter @ToString @EqualsAndHashCode
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

}
