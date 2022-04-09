package com.ecom;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	
	// Uncomment for h2 server console view
	/*@Bean
	org.h2.tools.Server h2Server() {
	    Server server = new Server();
	    try {
	        server.runTool("-tcp");
	        server.runTool("-tcpAllowOthers");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return server;

	}*/
}
