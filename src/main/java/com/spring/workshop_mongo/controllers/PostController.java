package com.spring.workshop_mongo.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.workshop_mongo.models.dtos.PostDTO;
import com.spring.workshop_mongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	private Instant instant;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		PostDTO dto = postService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		List<PostDTO> list = postService.findByTitle(text);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> findByTitle(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "start", defaultValue = "") String start,
			@RequestParam(value = "end", defaultValue = "") String end) {
		List<PostDTO> list = postService.fullSearch(text, start, end);
		return ResponseEntity.ok(list);
	}
}
