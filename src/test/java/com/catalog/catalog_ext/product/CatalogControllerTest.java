package com.catalog.catalog_ext.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cat.catalog.CatalogApplication;
import com.cat.catalog.product.CatalogRepository;
import com.cat.catalog.product.Product;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatalogApplication.class)
@AutoConfigureMockMvc
class CatalogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CatalogRepository catalogRepository;

	private <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	void testGetProductById() throws Exception {
		final Long productId = new Long(567838);
		final String productName = "Laptop";
		final Product mockProduct = new Product(productId, productName);

		Mockito.when(catalogRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/catalog/products/" + productId)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Product product = mapFromJson(content, Product.class);
		assertEquals(product.getName(), productName);
		assertEquals(product.getId().longValue(), productId.longValue());
	}

}
