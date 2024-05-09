package com.ecommerce.angularspring.Springbootecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.angularspring.Springbootecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
