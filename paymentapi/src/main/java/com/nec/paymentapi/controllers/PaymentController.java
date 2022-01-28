package com.nec.paymentapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nec.paymentapi.models.Payment;
import com.nec.paymentapi.services.OrderConsumer;
import com.nec.paymentapi.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
	private OrderConsumer orderConsumer;

    @Autowired
    private PaymentService paymentService;
    @PostMapping({"/v1.0/{orderId}", "/v1.1/{orderId}"})
    public ResponseEntity<?> addPayment(@PathVariable("orderId") long orderId,@RequestBody Payment payment){
    
    	Payment paymentObj=this.orderConsumer.savePayment(orderId,payment);
    	if(paymentObj!=null)
    	 return ResponseEntity.status(HttpStatus.ACCEPTED)
    			.body(paymentObj);
    	else
    		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	    			.body("Payment could not be processed or order not existing......");
    	
    }

    @PostMapping({"/v1.0/publish/{transactionId}", "/v1.1/publish/{transactionId}"})
    public ResponseEntity<?> publishPaymentData(@PathVariable("transactionId") 
    long transactionId){
    
    	this.paymentService.sendMessage(transactionId);
    	return ResponseEntity.status(HttpStatus.ACCEPTED)
    	    			.body("Payment published......");
    	
    }
}
