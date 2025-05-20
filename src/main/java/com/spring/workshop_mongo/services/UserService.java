package com.spring.workshop_mongo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.workshop_mongo.models.dtos.PostDTO;
import com.spring.workshop_mongo.models.dtos.UserDTO;
import com.spring.workshop_mongo.models.entities.Post;
import com.spring.workshop_mongo.models.entities.User;
import com.spring.workshop_mongo.respositories.UserRepository;
import com.spring.workshop_mongo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	
	private UserRepository userRepository;
	
	
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		return new UserDTO(getEntityById(id));
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User(dto);
		entity = userRepository.insert(entity);
		return new UserDTO(entity);
	}
	
	
	public UserDTO update(String id, UserDTO dto) {
		User entity = getEntityById(id);
		copyDtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		return new UserDTO(entity);
		
	}
	
	
	public void delete(String id) {
		getEntityById(id);
		userRepository.deleteById(id);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User entity = getEntityById(id);
		return entity.getPosts().stream().map(x -> new PostDTO(x)).toList();
	}
	
	public User getEntityById(String id) {
		Optional<User> result = userRepository.findById(id);
		User entity = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		return entity;
	}
	
	public void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
