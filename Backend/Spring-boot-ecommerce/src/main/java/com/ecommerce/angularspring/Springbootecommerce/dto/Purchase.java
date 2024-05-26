package com.ecommerce.angularspring.Springbootecommerce.dto;

import java.util.Set;

import com.ecommerce.angularspring.Springbootecommerce.entity.Address;
import com.ecommerce.angularspring.Springbootecommerce.entity.Customer;
import com.ecommerce.angularspring.Springbootecommerce.entity.Order;
import com.ecommerce.angularspring.Springbootecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
	
}
