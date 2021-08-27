package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

// This is our Main Driver class.
public class Driver {

	public static void main(String[] args) {

		try {
			
			// craete object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file (data/sample-lite.json) and map/convert to Java POJO.
			Student theStudent = mapper.readValue(new File("data/sample-lite.json"), Student.class);
			// Above, we are reading from sample-lite.json file which is in data directory.
			
			// print first name and last name
			System.out.println("First name: " + theStudent.getFirstName());
			System.out.println("Last name: " + theStudent.getLastName());
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
