package com.revature.main;

import static org.assertj.core.api.Assertions.assertThat;
import com.revature.main.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NftPublisherApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        //assertThat(userController).isNotNull();
    }
}
