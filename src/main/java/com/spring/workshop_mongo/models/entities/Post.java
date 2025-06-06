package com.spring.workshop_mongo.models.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.workshop_mongo.models.dtos.PostDTO;
import com.spring.workshop_mongo.models.embedded.Author;
import com.spring.workshop_mongo.models.embedded.Comment;

@Document(collection = "posts")
public class Post {
	
	@Id
	private String id;
	private Instant moment;
	private String title;
	private String body;
	private Author author;
	
	private List<Comment> comments = new ArrayList<>();
	
	
	
	public Post() {
		
	}
	

	public Post(String id, Instant moment, String title, String body, Author author) {
		
		this.id = id;
		this.moment = moment;
		this.title = title;
		this.body = body;
		this.author = author;
	}
	
	public Post(PostDTO dto) {
			
			this.id = dto.getId();
			this.moment = dto.getMoment();
			this.title = dto.getTitle();
			this.body = dto.getBody();
			this.author = dto.getAuthor();
			this.comments.addAll(dto.getComments());
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	
	
	
}
