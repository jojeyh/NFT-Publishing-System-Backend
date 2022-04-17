package com.revature.main.integration;

import com.revature.main.model.User;
import com.revature.main.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DirtiesContext

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test_getUserByUsernameAndPassword_validCredentials() {

        User actual = userRepository.findByUsernameAndPassword("john_smith", "pass12");

        User expected = new User();
        expected.setId(1L);
        expected.setUsername("john_smith");
        expected.setPassword("pass12");
        expected.setEthAddress("aox12");

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void test_getUserByUsernameAndPassword_invalidCredentials() {
        // Act
        User actual = userRepository.findByUsernameAndPassword("invalid", "1234");

        // Assert
        Assertions.assertNull(actual);
    }
}
