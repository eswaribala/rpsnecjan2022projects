package com.nec.orderapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.nec.orderapi.facades.OrderFacade;
import com.nec.orderapi.models.Order;

@Service
public class OrderService {

	@Autowired
	private OrderFacade orderFacade;
	
	public boolean publishData(Order order) {
		
		MessageChannel messageChannel = orderFacade.orderOut();
	       return  messageChannel.send(MessageBuilder
	                .withPayload(order)
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());


	}
	
}
