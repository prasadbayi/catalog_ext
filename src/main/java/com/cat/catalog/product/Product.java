package com.cat.catalog.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	public Product() {
		super();
	}
	
	public Product(Long productId, String productName) {
		super();
		this.id = productId;
		this.name = productName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long productId) {
		this.id = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
	}
}
