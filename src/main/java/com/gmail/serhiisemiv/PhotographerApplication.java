package com.gmail.serhiisemiv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotographerApplication {
	private static final Logger info = LoggerFactory.getLogger("com.gmail.serhiisemiv.info");
	private static final Logger logger = LoggerFactory.getLogger(PhotographerApplication.class);
	public static void main(String[] args) {
		SpringApplication application =
				new SpringApplication(PhotographerApplication.class);
		application.run(args);
		info.info("PhotographerApplication started!!!");
	}
}
