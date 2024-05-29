package com.ecommerce.angularspring.Springbootecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.angularspring.Springbootecommerce.entity.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long>{
	Page<Order> findByCustomerEmail(String email, Pageable pageable);
}
