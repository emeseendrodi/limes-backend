package com.limes.backend;

import com.limes.backend.persistence.DatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimesBackendApplication.class, args);
                DatabaseFactory.setUp();
	}

}
