package com.technorose.techrose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TechroseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechroseApplication.class, args);
	}

}
