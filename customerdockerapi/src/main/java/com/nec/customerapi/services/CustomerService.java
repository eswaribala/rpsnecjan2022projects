package com.nec.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nec.customerapi.models.Address;
import com.nec.customerapi.models.Customer;
import com.nec.customerapi.repositories.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepo customerRepo;
    
    //insert
    
    public Customer addCustomer(Customer customer) {
    	
    	if(customer.getAddressList().size()>0) {
    		for(Address address : customer.getAddressList()) {
    			address.setCustomer(customer);
    		}
    	}
    	return this.customerRepo.save(customer);
    }
    
    //select 
    
    public List<Customer> getAllCustomers(){
    	return this.customerRepo.findAll();
    }
    
    //select by Id
    
    public Customer getCustomerById(long customerId) {
    	return this.customerRepo.findById(customerId).orElse(null);
    }
    
    //delete by Id
    
    public boolean deleteCustomerById(long customerId) {
    	boolean status=false;
    	this.customerRepo.deleteById(customerId);
    	if(this.getCustomerById(customerId)==null) {
    		status=true;
    	}
    	return status;
    }
    
    //update 
  public Customer updateCustomer(Customer customer) {    	
    	
    	return this.customerRepo.save(customer);
    }
    
}
