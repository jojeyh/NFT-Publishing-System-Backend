package com.revature.main.repository;

import com.revature.main.model.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    @Query("SELECT image FROM Image image WHERE image.author.id = ?1")
    Iterable<Image> findImagesByUserId(Long id);

}
