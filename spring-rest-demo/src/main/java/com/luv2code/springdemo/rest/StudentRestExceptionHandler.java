package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Special Annotation Spring Framework for defining Global Exception Handler.
public class StudentRestExceptionHandler {

	// add exception handling code here.
	
	// Add an Exception Handler for our new custom StudentNotFoundException using @ExceptionHandler.
	
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
		// So this is an Exception Handler method.
		// ResponseEntity<StudentErrorResponse> - type of the response body is StudentErrorResponse. (See StudentErrorResponse class).
		// handleException(StudentNotFoundException exc) - can handle/catch StudentNotFoundException type of Exception. (See StudentNotFoundException class).
			
			
			// create a StudentErrorResponse
			StudentErrorResponse error = new StudentErrorResponse();
			
			// set the values
			error.setStatus(HttpStatus.NOT_FOUND.value()); // This is 404 error
			error.setMessage(exc.getMessage());
		    error.setTimeStamp(System.currentTimeMillis());
			
		    
			// return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			// Here, 'error' is the body and 'HttpStatus.NOT_FOUND' is the status code.
			
			// So Jackson will take our custom Exception POJO class converting it to JSON accordingly.
			// And we will see status, message, time stamp as JSON response in browser after giving bad request.
		}
		
		
		///// GENERIC EXCEPTION HANDLER /////
		// As we can see in the output, if we pass any characters instead of integer as a path variable
		// in the URL, then we are getting an Exception. And we don't have an Exception handler for that.
		// In above handler, we were only checking the integer bounds with the size of ArrayList.
		
		// add another exception handler... doing catch all... to catch any Exception
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
		// So here, we are giving 'Exception exc' which will get all Exceptions (Generic).
			
			// create a StudentErrorResponse
			StudentErrorResponse error = new StudentErrorResponse();
			
			// set the values
			error.setStatus(HttpStatus.BAD_REQUEST.value()); // This is 400 error
			error.setMessage(exc.getMessage());
		    error.setTimeStamp(System.currentTimeMillis());
			
		    
			// return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
			// Here, 'error' is the body and 'HttpStatus.BAD_REQUEST' is the status code.
		}
}
