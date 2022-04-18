package com.revature.main.repository;

import com.revature.main.model.NFT;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftRepository extends CrudRepository<NFT, Long> {
	public Iterable<NFT> findNFTByOwner(Long id);
}
