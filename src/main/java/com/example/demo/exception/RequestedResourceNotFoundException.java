package com.example.demo.exception;

public class RequestedResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestedResourceNotFoundException(String message) {
		super(message);
	}

	
}
