package com.spring.workshop_mongo.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 782719540769618476L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
