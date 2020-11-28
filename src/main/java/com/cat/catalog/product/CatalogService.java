package com.cat.catalog.product;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
	final Logger log = LogManager.getLogger(CatalogService.class);
	
	@Autowired(required = true)
	private CatalogRepository catalogRepository;

	public Product getProductById(Long id) {
		log.debug("CatalogService.getProducts() Fetching Product Details of " + id);
		
		try {
		Optional<Product> product = catalogRepository.findById(id);
		if (!product.isPresent()) {
			throw new ProductNotFoundException("id = " + id);
		}
		return product.get();
		}
		catch(Exception e) {
			log.error(e.toString());
			return null;
		}
	}
}
