package com.example.myrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomizedJpaRepositoryImpl.class)
public class MyrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrestApplication.class, args);
	}

}
