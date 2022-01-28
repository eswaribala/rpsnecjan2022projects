package com.nec.paymentapi.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("payments")
public class Payment {
    @Id
	private long transactionId;
	private boolean paymentStatus;
	private PaymentMode paymentMode;
	private Order order;
}
