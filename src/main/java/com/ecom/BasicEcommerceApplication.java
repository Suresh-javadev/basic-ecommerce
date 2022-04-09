package com.ecom;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicEcommerceApplication {

	@PostConstruct
	public void init() {
		// Setting Spring Boot Default time zone to India
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(BasicEcommerceApplication.class, args);
	}

}
