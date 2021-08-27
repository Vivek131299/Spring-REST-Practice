package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

// This is our Main Driver class.
public class Driver {

	public static void main(String[] args) {

		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file (data/sample-lite.json) and map/convert to Java POJO.
			Student theStudent = mapper.readValue(new File("data/sample-lite.json"), Student.class);
			// Above, we are reading from sample-lite.json file which is in data directory.
			
			// read JSON file (data/sample-full.json) and map/convert to Java POJO.
			Student theStudent2 = mapper.readValue(new File("data/sample-full.json"), Student.class);
			// We have to also create a new Address class for Address Nested field in this
			// sample-full.json file. (See Address class).
			// AND then we need to Add this newly created Address type field into our Student class 
			// and also appropriate getter/setter method for it.
			// AND the Finally, to store languages field from json file, we need to add String array
			// in our Student class because in json file, we have languages stored in an array.
			
			// print first name and last name
			System.out.println("First name: " + theStudent.getFirstName());
			System.out.println("Last name: " + theStudent.getLastName());
			
			// print out address: street and city
			Address tempAddress = theStudent2.getAddress();
			System.out.println("Street = " + tempAddress.getStreet());
			System.out.println("City = " + tempAddress.getCity());
			
			// print out languages
			for (String tempLang : theStudent2.getLanguages()) {
				System.out.println(tempLang);
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
