package com.spring.workshop_mongo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.workshop_mongo.models.dtos.UserDTO;
import com.spring.workshop_mongo.models.entities.User;
import com.spring.workshop_mongo.respositories.UserRepository;

@Service
public class UserService {
	@Autowired
	
	private UserRepository userRepository;
	
	
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
}
