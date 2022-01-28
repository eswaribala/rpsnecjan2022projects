package com.nec.stockapi.facades;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeliveryFacade {

	String Output="delivery-out";
	@Output(Output)
	MessageChannel deliveryOut();
}
