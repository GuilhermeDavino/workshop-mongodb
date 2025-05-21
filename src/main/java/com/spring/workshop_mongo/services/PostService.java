package com.spring.workshop_mongo.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.workshop_mongo.models.dtos.PostDTO;
import com.spring.workshop_mongo.models.entities.Post;
import com.spring.workshop_mongo.respositories.PostRepository;
import com.spring.workshop_mongo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {
	@Autowired
	
	private PostRepository postRepository;
	
	
	public PostDTO findById(String id) {
		return new PostDTO(getEntityById(id));
	}
	
	
	public List<PostDTO> findByTitle(String title) {	
		List<Post> list = postRepository.searchTitle(title);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
	
	public List<PostDTO> fullSearch(String title, String start, String end) {	
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0));
		Instant endMoment = convertMoment(end, Instant.now());
		List<Post> list = postRepository.fullSearch(title, startMoment, endMoment);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
	
	
	public PostDTO insert(PostDTO dto) {
		Post entity = new Post(dto);
		entity = postRepository.insert(entity);
		return new PostDTO(entity);
	}
	
	
	public PostDTO update(String id, PostDTO dto) {
		Post entity = getEntityById(id);
		copyDtoToEntity(dto, entity);
		entity = postRepository.save(entity);
		return new PostDTO(entity);
		
	}
	
	
	public void delete(String id) {
		getEntityById(id);
		postRepository.deleteById(id);
	}
	
	
	public Post getEntityById(String id) {
		Optional<Post> result = postRepository.findById(id);
		Post entity = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		return entity;
	}
	
	public void copyDtoToEntity(PostDTO dto, Post entity) {
		entity.setAuthor(dto.getAuthor());
		entity.setBody(dto.getBody());
		entity.setMoment(dto.getMoment());
		entity.setTitle(dto.getTitle());
		
	}
	
	
	private Instant convertMoment(String originalString, Instant alternative) {
		try {
			return Instant.parse(originalString);
		} catch (DateTimeParseException e) {
			return alternative;
		}
		
	}
}
