package com.ecommerce.angularspring.Springbootecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo {
	private int amount;
	private String currency;
	private String receiptEmail;
}
