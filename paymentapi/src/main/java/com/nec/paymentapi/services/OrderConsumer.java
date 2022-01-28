package com.nec.paymentapi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nec.paymentapi.models.Order;
import com.nec.paymentapi.models.Payment;
import com.nec.paymentapi.repositories.PaymentRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {
    @Autowired
	private PaymentRepo paymentRepo;
	private Order order;
    
	@KafkaListener(topics = "${order.topic.name}", 
			groupId = "${order.topic.group.id}")
    public void consume(String message) {
		log.info(String.format("Message recieved -> %s", message));		
		Gson gson=new Gson();		
		order= gson.fromJson(message, Order.class);
		  
		log.info(""+order.getOrderId());	
		
	}
	
	
	public Payment savePayment(long orderId,Payment payment) {
		
		if((order!=null)&&(orderId==order.getOrderId()))
		{
		 payment.setOrder(order);
		 return this.paymentRepo.save(payment);
		}
		else
			return null;
	}

}
