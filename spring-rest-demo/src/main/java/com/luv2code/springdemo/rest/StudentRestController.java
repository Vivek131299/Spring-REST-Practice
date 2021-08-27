package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
		
		// just index into the list
		
		return theStudents.get(studentId); // So 'studentId' here is the index.
	}
}
