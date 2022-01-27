package com.nec.customerapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nec.customerapi.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long>{

}
