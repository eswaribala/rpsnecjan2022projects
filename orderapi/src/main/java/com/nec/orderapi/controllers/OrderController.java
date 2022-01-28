package com.nec.orderapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nec.orderapi.models.Order;
import com.nec.orderapi.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping({"/v1.0", "/v1.1"})
	public ResponseEntity<?> publishOrder(@RequestBody Order order){
		if(this.orderService.publishData(order)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body("Order with"+order.getOrderId() +"Published");
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body("Order with"+order.getOrderId() +"Not Published");
	}
	
}
