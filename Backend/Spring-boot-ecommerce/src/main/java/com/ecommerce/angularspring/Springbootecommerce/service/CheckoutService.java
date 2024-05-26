package com.ecommerce.angularspring.Springbootecommerce.service;

import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
