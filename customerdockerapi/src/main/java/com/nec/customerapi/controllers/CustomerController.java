package com.nec.customerapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.nec.customerapi.models.Customer;
import com.nec.customerapi.services.CustomerService;

@RestController
@RequestMapping("/customers")

public class CustomerController {
	@Autowired
	private CustomerService customerService;

	
	
	
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
	
	@GetMapping({"/v1.0", "/v1.1"})
	public List<Customer> getAllCustomers(){
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId){
		
		Customer customerObj=this.customerService.getCustomerById(customerId);
		if(customerObj!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
			
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data not found");
		
	}
	
	@DeleteMapping({"/v1.0/{customerId}", "/v1.1/{customerId}"})
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId){
		
		
		if(this.customerService.deleteCustomerById(customerId)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer"+ customerId+"Deleted");
			
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data not found");
		
	}
	
	
	//update customer
		@PutMapping({"/v1.0", "/v1.1"})
		public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
			
			Customer customerObj=this.customerService.updateCustomer(customer);
			if(customerObj!=null) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
				
			}
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Data incorrect");
			
		}
		/*
		 * 
    http://localhost:7070/customers/filters/v1.0/1?fields=name.firstName,name.lastName
    http://localhost:7070/customers/filters/1?fields=-customerId,-name,-addresses

		 */
		
		@GetMapping({"/filters/v1.0/{customerId}", "filters/v1.1/{customerId}"})
		public ResponseEntity<?> findCustomerById(@RequestParam(name = "fields", required = false) 
	    String fields, @PathVariable("customerId") long customerId)
		{
	    	
	    	Map<Object,Object> model=new HashMap<>();
	    	
	    	Customer customer = this.customerService.getCustomerById(customerId);
	    	
	    	if(customer!=null)
	    	{
	    		//fields refers to runtime selection
	    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
				return ResponseEntity.ok(SquigglyUtils.stringify(mapper, customer));

	    	}
	    	else
	    	{
		         model.put("message", "customer not existing");
			        
		         return ResponseEntity.ok(model);
	    	}

		}

	
}
