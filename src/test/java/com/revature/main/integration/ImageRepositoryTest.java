package com.revature.main.integration;

import com.revature.main.model.Image;
import com.revature.main.model.User;
import com.revature.main.repository.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest

    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)


public class ImageRepositoryTest {


    @Autowired
    private ImageRepository imageRepository;

    @Test
    @Transactional
    public void test_findImagesByUserId() {

        Iterable<Image> actual = imageRepository.findImagesByUserId(1L);

        User user= new User();
        user.setId(1L);
        user.setUsername("john_smith");
        user.setPassword("pass12");
        user.setEthAddress("aox12");

        Iterable<Image> expected = new ArrayList<Image>();
        Image image = new Image();
        image.setId(1L);
        image.setImageURL("url12");
        image.setAuthor(user);
        ((ArrayList<Image>) expected).add(image);

        Assertions.assertEquals(expected, actual);
    }
}