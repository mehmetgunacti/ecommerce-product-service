package com.wolf359apps.ecommerce.products.v1.controller;

import com.wolf359apps.ecommerce.products.v1.dto.ProductDTO;
import com.wolf359apps.ecommerce.products.v1.entity.Product;
import com.wolf359apps.ecommerce.products.v1.mapper.ProductMapper;
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
	private final ProductMapper  productMapper;

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {

		Product product      = productMapper.toEntity(dto);
		Product savedProduct = productService.save(product);
		return new ResponseEntity<>(
				productMapper.toDto(savedProduct),
				HttpStatus.CREATED
		);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {

		return productService.getById(id)
				.map(product -> new ResponseEntity<>(
								productMapper.toDto(product),
								HttpStatus.OK
						)
				)
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {

		List<ProductDTO> products =
				productService
						.getAll()
						.stream()
						.map(productMapper::toDto)
						.toList();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> putProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

		return productService
				.update(id, updatedProduct)
				.map(product -> new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}

