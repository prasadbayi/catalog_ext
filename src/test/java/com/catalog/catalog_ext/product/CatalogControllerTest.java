package com.catalog.catalog_ext.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cat.catalog.AbstractTest;
import com.cat.catalog.product.Product;

class CatalogControllerTest extends AbstractTest {
	private static final Long id = new Long(1);
	private static final String name = "Joy Stick";

	@Override
	@Before
	public void setUp() {
		super.setUp();
		loadSampleProduct();
	}
	
	void loadSampleProduct() {
		String urlString = "http://localhost:8082/catalog/create_product/" + name;
		String endpoint = "";
		try {
			URL url = new URL(urlString);
			endpoint = url.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			MvcResult mvcResult = mvc
					.perform(MockMvcRequestBuilders.post(endpoint).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			int status = mvcResult.getResponse().getStatus();
			assertEquals(201, status);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Test method for
	 * {@link com.tmyretail.ext_catalog.CatalogController#getProductById(java.lang.Long)}.
	 */
	@Test
	void testGetProducts() throws Exception {
		setUp();
		String urlString = "http://localhost:8080/catalog/products/" + id;
		String endpoint = "";
		try {
			URL url = new URL(urlString);
			endpoint = url.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(endpoint).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product product = super.mapFromJson(content, Product.class);
		assertTrue(product.getName().equals(name));
		assertTrue(product.getId().longValue() == id);
	}
	
	/**
	 * Test method for
	 * {@link com.tmyretail.ext_catalog.CatalogController#getAllProducts()}.
	 */
	@Test
	void testGetAllProducts() throws Exception {
		setUp();
		String urlString = "http://localhost:8080/catalog/products";
		String endpoint = "";
		try {
			URL url = new URL(urlString);
			endpoint = url.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(endpoint).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}
}
