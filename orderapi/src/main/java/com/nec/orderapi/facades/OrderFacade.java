package com.nec.orderapi.facades;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderFacade {

	String output="order-out";
	@Output(output)
	MessageChannel orderOut();
}
