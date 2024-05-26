package com.ecommerce.angularspring.Springbootecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.angularspring.Springbootecommerce.dao.CustomerRepository;
import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	@Override
	public PurchaseResponse placeOrder(Purchase purchase) {
		// TODO Auto-generated method stub
		return null;
	}

}
