package com.nec.customerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nec.customerapi.models.Customer;
import com.nec.customerapi.services.CustomerService;

@RestController
@RequestMapping("/customers")
@RefreshScope
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	 @Value("${newmessage}")
	private String message;
	@GetMapping({"/v1.0", "/v1.1"})
	public ResponseEntity<?> getMessage(){
		
	   if((message!=null)&&(message.length()>0))
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
	   else
		   return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Message Not Available");  
	}
	
	
	//add customer
	@PostMapping({"/v1.0", "/v1.1"})
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		
		Customer customerObj=this.customerService.addCustomer(customer);
		if(customerObj!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
			
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data incorrect");
		
	}
	
	
}
