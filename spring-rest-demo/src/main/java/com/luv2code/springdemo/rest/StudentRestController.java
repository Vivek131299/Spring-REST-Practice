package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;
	
	// Using @PostConstruct Annotation to load the Students data because 
	// @PostConstruct is called ONLY ONCE when this given Bean is constructed.
	// So now we solved the previous problem of loading the Students each time we send request by
	// loading the Students in /students Mapping (in getStudents() method).
	
	@PostConstruct
	public void loadData() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rossi"));
		theStudents.add(new Student("Mary", "Smith"));
	}
	
	
	// define an endpoint for "/students"... This will return list of students.
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudents;
	}
	
	
	// define an endpoint for "/students/{studentId}" - For retrieving a single student by id.
	// So we will return student it by index of that ArrayList.
	
	///// @PathVariable ANNOTATION / PATH VARIABLE /////
	// As we know we can pass path variable in the URL. like- /students/0 - This will give the first student from the list.
	// So, here that '0' in the URL is Path Variable.
	// So we have to define it in our Request Mapping in URL.
	// For that we use {} (curly braces) to enclose that Path Variable into the URL while Request Mapping,
	// like- @GetMapping("/students/{studentId}"). So here 'studentId' is the Path Variable whose value can be 0,1,2,3,etc..
	//
	// So, after this, we need to bind this passed Path Variable to our parameter in method for accessing it.
	// So we use @PathVariable ANNOTATION before a parameter in our method,
	// like- public Student getStudent(@PathVariable int studentId) {}
	// NOTE that the parameter name 'studentId' MUST BE SAME as that of Path Variable name in Mapping ("/students/{studentId}")
	// And now we can access that variable in the method.
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		///// Updating this REST Service/Controller to throw the Exception /////
		// As we have seen earlier, if we pass path variable out of bound in the URL 
		// (for e.g.- if we pass /students/9999, while list has only 10 elements), then we 
		// get the Exception and error page on the browser.
		// To handle this Exception, we created our custom StudentNotFoundException.
		// (See StudentNotFoundException class and StudentErrorResponse class).
		
		// So, check the studentId against list size
		if ((studentId >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		
		// just index into the list
		return theStudents.get(studentId); // So 'studentId' here is the index.
	}
	
	
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
