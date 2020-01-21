package com.SpringAppVersion2;

import com.SpringAppVersion2.service.JavaCustomMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
@EnableJpaAuditing
public class SpringApplicationVersion2{

	@Autowired
	JavaCustomMailSender javaCustomMailSender;




	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationVersion2.class, args);
	}
}

