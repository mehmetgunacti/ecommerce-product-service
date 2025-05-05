package com.wolf359apps.ecommerce.products.v1.service;

import com.wolf359apps.ecommerce.products.v1.entity.Product;
import com.wolf359apps.ecommerce.products.v1.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void testGetAllProducts() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Laptop");
		product.setPrice(BigDecimal.valueOf(1500));

		when(productRepository.findAll()).thenReturn(List.of(product));

		List<Product> products = productService.getAllProducts();

		assertFalse(products.isEmpty());
		assertEquals(1, products.size());
		assertEquals("Laptop", products.get(0).getName());
	}

	@Test
	void testGetProductById() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Laptop");

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Optional<Product> foundProduct = productService.getProductById(1L);

		assertTrue(foundProduct.isPresent());
		assertEquals("Laptop", foundProduct.get().getName());
	}

	@Test
	void testSaveProduct() {
		Product product = new Product();
		product.setName("Smartphone");
		product.setPrice(BigDecimal.valueOf(800));

		when(productRepository.save(product)).thenReturn(product);

		Product savedProduct = productService.saveProduct(product);

		assertNotNull(savedProduct);
		assertEquals("Smartphone", savedProduct.getName());
	}

	@Test
	void testDeleteProduct() {

		Long productId = 1L;
		productService.deleteProduct(productId);
		verify(productRepository, times(1)).deleteById(productId);

	}

}
