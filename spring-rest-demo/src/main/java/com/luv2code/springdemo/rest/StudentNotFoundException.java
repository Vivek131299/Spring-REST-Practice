package com.luv2code.springdemo.rest;

// This is our custom Exception class.
public class StudentNotFoundException extends RuntimeException{
	
	// generate constructors from Super class.

	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
