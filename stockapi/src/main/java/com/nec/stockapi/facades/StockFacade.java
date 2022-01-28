package com.nec.stockapi.facades;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface StockFacade {
	
	String Input="payment-in";
	@Input(Input)
	MessageChannel paymentIn();

}
