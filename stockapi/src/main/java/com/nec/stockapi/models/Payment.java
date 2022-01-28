package com.nec.stockapi.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Payment {
    
	private long transactionId;
	private boolean paymentStatus;
	private PaymentMode paymentMode;
	private Order order;
}
