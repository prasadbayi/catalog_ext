package com.cat.catalog.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalog")
public class CatalogController {
	private Logger log = LogManager.getLogger(CatalogController.class);

	@Autowired(required = true)
	private CatalogService catalogService;

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Long id) throws Exception {
		log.debug("CatalogController.getProduct() Fetching Product Details of " + id);
		return catalogService.getProductById(id);
	}
}
