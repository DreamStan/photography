package com.photograph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableWebSecurity
@SpringBootApplication
@MapperScan("com.photograph.dao")
public class PhotographApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotographApplication.class, args);
	}
}
