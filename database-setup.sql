drop table if exists nps_images;
drop table if exists nps_nfts;
drop table if exists nps_users;

create table nps_users (
  nps_users_id serial primary key,
  nps_users_username varchar(45),
  nps_users_password varchar(43),
  nps_users_eth_address bytea
);

create table nps_images (
  nps_images_id serial primary key,
  nps_images_image bytea not null,
  nps_images_author int not null,
  nps_images_contract_address bytea,
  foreign key (nps_images_author) references nps_users(nps_users_id)
);

create table nps_nfts (
  nft_id serial primary key,
  nft_symbol varchar(5),
  nft_contract_address bytea not null unique,
  nft_name varchar(45),
  nft_token_id bytea not null,
  nft_owner bytea not null,
  nft_token_uri varchar(200) not null,
  nft_author int not null,
  foreign key (nft_author) references nps_users(nps_users_id)
);
