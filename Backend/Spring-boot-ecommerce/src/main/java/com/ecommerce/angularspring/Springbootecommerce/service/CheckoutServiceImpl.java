package com.ecommerce.angularspring.Springbootecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.angularspring.Springbootecommerce.dao.CustomerRepository;
import com.ecommerce.angularspring.Springbootecommerce.dto.PaymentInfo;
import com.ecommerce.angularspring.Springbootecommerce.dto.Purchase;
import com.ecommerce.angularspring.Springbootecommerce.dto.PurchaseResponse;
import com.ecommerce.angularspring.Springbootecommerce.entity.Customer;
import com.ecommerce.angularspring.Springbootecommerce.entity.Order;
import com.ecommerce.angularspring.Springbootecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Data
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {
		this.customerRepository = customerRepository;
		Stripe.apiKey = secretKey;
	}
	
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
	
	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", "Luv2Shop purchase");
		params.put("receipt_email", paymentInfo.getReceiptEmail());
		return PaymentIntent.create(params);
	}

}
