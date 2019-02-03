package com.alex.cursospringudemy.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alex.cursospringudemy.services.DBService;
import com.alex.cursospringudemy.services.EmailService;
import com.alex.cursospringudemy.services.MockEmailService;

@Configuration
@Profile("test")
public class TesteConfig {
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateDatabase();		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
