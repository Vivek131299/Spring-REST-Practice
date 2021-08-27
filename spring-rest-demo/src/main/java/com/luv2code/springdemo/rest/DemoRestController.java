package com.luv2code.springdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This will add REST support for this Controller class.
@RequestMapping("/test") // Map this Controller class to the "/test" path/URL request.
public class DemoRestController {

	// Add code for the "/hello" endpoint
	
	@GetMapping("/hello")
	public String sayHello() {
		
		return "Hello World!";
	}
}