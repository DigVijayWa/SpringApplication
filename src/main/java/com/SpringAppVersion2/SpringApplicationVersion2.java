package com.SpringAppVersion2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class SpringApplicationVersion2 {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationVersion2.class, args);
	}
}
