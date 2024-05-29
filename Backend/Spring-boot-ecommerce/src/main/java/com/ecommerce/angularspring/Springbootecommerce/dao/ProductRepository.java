package com.ecommerce.angularspring.Springbootecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.angularspring.Springbootecommerce.entity.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(@Param("name") String name, Pageable page);

}
