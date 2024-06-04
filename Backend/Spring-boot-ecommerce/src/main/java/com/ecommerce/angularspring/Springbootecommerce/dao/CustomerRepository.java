package com.ecommerce.angularspring.Springbootecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.angularspring.Springbootecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByEmail(String theEmail);
}
