package com.cat.catalog.product;

import java.net.URI;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("catalog")
@CrossOrigin(origins = "*")
public class CatalogController {

	private Logger log = LogManager.getLogger(CatalogController.class);

	@Autowired
	private CatalogRepository catalogRepository;

	@GetMapping("/products/{id}")
	public Product getProducts(@PathVariable Long id) throws Exception {
		log.debug("CatalogController.getProducts() Fetching Product Details of " + id);
		
		Optional<Product> product = catalogRepository.findById(id);
		if (!product.isPresent()) {
			throw new ProductNotFoundException("id-" + id);
		}
		return product.get();
	}
	
	@GetMapping("/products")
	public Iterable<Product> getProducts()   throws Exception {
		log.debug("CatalogController.getProducts() Fetching all Product Details");
		return catalogRepository.findAll();
	}
	
	@PostMapping("/create_product/{name}")
	public ResponseEntity<Object> createProduct(@PathVariable String name) {
		log.debug("CatalogController.createProduct() Creatign Product with name = "+name);
		Product newProduct = new Product(new Long(0), name);
		Product savedProduct = catalogRepository.save(newProduct);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
