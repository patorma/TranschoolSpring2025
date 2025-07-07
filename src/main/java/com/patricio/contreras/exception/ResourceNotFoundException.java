package com.patricio.contreras.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException () {
		super();
	}
	
	public ResourceNotFoundException (String message) {
		super(message);
	}
}
