insert into NPS_USERS
(NPS_USERS_ID ,  NPS_USERS_USERNAME , NPS_USERS_PASSWORD , NPS_USERS_ETH_ADDRESS)
values
(1L, 'john_smith', 'pass12', 'aox12'),
(2L, 'jane_smith', 'pass21', 'aox21');


insert into NPS_IMAGES
(ID , NPS_IMAGE_URL, AUTHOR_NPS_USERS_ID  )
values
(1L, 'url12', 1L);

insert into NPS_NFTS
(ID , NFT_CONTRACT_ADDRESS , NFT_NAME , NFT_OWNER , NFT_SYMBOL , NFT_TOKEN_ID , NFT_TOKEN_URI , NPS_IMAGE)
values
(1L,'dsa', 'nefs', 1L, 'nfs', 'id1', 'asd', 1L);
