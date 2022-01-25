package com.nec.customerapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RefreshScope
public class CustomerController {

	 @Value("${newmessage}")
	private String message;
	@GetMapping({"/v1.0", "/v1.1"})
	public ResponseEntity<?> getMessage(){
		
	   if((message!=null)&&(message.length()>0))
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
	   else
		   return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Message Not Available");  
	}
	
	
	
}
