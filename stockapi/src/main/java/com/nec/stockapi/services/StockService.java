package com.nec.stockapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.nec.stockapi.facades.DeliveryFacade;
import com.nec.stockapi.facades.StockFacade;
import com.nec.stockapi.models.Payment;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
    @Autowired
	private StockFacade stockFacade;
    
    @StreamListener(target = StockFacade.Input)
    @SendTo(DeliveryFacade.Output)
    public boolean consumePaymentData(@Payload Payment payment) {
    	boolean status=false;
    	log.info("Payment Status"+payment.getOrder().getOrderId());
    	log.info("Payment Status"+payment.isPaymentStatus());
    	if(payment.isPaymentStatus())
    		status=true;
    	
    	return status;
    }
    
}
