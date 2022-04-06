package com.revature.main;

import com.revature.main.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NftPublisherApplicationTests {

	@Autowired
	private UserController controller;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}

}
