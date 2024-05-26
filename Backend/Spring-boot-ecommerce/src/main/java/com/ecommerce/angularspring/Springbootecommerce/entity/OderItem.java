package com.ecommerce.angularspring.Springbootecommerce.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "product_id")
	private Long productId;
}
