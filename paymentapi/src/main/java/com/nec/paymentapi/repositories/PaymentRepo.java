package com.nec.paymentapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nec.paymentapi.models.Payment;

public interface PaymentRepo extends MongoRepository<Payment,Long>{

}
