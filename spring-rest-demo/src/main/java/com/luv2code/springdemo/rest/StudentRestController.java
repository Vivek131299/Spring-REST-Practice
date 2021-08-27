package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	// define an endpoint for "/students"... This will return list of students.
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		// create some sample students to return.
		// hard coding students for now.
		
		List<Student> theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rossi"));
		theStudents.add(new Student("Mary", "Smith"));
		
		return theStudents;
	}
}
