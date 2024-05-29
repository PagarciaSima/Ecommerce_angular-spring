package com.ecommerce.angularspring.Springbootecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.angularspring.Springbootecommerce.dao.CustomerRepository;
import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;
import com.ecommerce.angularspring.Springbootecommerce.entity.Customer;
import com.ecommerce.angularspring.Springbootecommerce.entity.Order;
import com.ecommerce.angularspring.Springbootecommerce.entity.OrderItem;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Data
public class CheckoutServiceImpl implements CheckoutService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// Retrieve the order info from dto
		Order order = purchase.getOrder();
		// Generate tracking number
		String orderTrackingNumber = generatedOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		// Populate order with orderItems
		Set<OrderItem>orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		// Populate order with billing and shipping address
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());

		// Populate customer with order
		Customer customer = purchase.getCustomer();
		
		// Check if this is an existing customer
		String theEmail = customer.getEmail();
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		if(customerFromDB != null) {
			customer = customerFromDB;
		}
		
		customer.add(order);
		// Save to the DB
		customerRepository.save(customer);
		// Return response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generatedOrderTrackingNumber() {
		// Generate random UUID number
		return UUID.randomUUID().toString();
	}

}
