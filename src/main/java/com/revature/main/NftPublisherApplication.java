package com.revature.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NftPublisherApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NftPublisherApplication.class, args);
	}

}