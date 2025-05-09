package com.wolf359apps.ecommerce.products.v1.controller;

import com.wolf359apps.ecommerce.products.v1.entity.Product;
import com.wolf359apps.ecommerce.products.v1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		Product savedProduct = productService.save(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {

		return productService.getById(id)
				.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

		return productService
				.update(id, updatedProduct)
				.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}

