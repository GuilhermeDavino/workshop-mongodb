package com.spring.workshop_mongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.spring.workshop_mongo.models.entities.User;
import com.spring.workshop_mongo.respositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
	
	
}
