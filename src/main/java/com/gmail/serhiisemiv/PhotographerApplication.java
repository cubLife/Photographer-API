package com.gmail.serhiisemiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotographerApplication {

	public static void main(String[] args) {
		SpringApplication application =
				new SpringApplication(PhotographerApplication.class);
		application.setAdditionalProfiles("prod");
		application.run(args);
	}

}
