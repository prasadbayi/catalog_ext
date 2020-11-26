package com.cat.catalog.product;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String exception) {
		super(exception);
	}
}