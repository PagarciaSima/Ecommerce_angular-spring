package com.ecommerce.angularspring.Springbootecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;
import com.ecommerce.angularspring.Springbootecommerce.service.CheckoutService;

import lombok.AllArgsConstructor;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {
	
	private CheckoutService checkoutService;
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		return purchaseResponse;
		
	}
}
