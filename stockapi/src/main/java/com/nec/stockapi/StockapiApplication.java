package com.nec.stockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import com.nec.stockapi.facades.DeliveryFacade;
import com.nec.stockapi.facades.StockFacade;
import com.nec.stockapi.models.Payment;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class StockapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockapiApplication.class, args);
	}

}
