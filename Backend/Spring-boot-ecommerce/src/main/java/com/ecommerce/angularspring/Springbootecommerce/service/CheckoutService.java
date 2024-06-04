package com.ecommerce.angularspring.Springbootecommerce.service;

import com.ecommerce.angularspring.Springbootecommerce.dto.PaymentInfo;
import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
